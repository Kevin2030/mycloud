package com.kingdee.internet.finance.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingdee.internet.finance.config.ConfigConst;

public class AuthManager {

	private static final Logger	logger				= LogManager.getLogger(AuthManager.class);
	private static final int	CONNECTION_TIME_OUT	= 50000;
	private static final int	READ_TIME_OUT		= 300000;
	private static final String	ENC					= "UTF-8";
	private static final String	AUTHORIZATION		= "authorization";

	public static String auth() {
		BufferedReader br = null;
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(ConfigConst.CONSTANT.authURL);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RequestMethod.POST.name());
			conn.setRequestProperty(AUTHORIZATION,
					appAuth2Treaty(ConfigConst.CONSTANT.appID, ConfigConst.CONSTANT.appSecret));
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(CONNECTION_TIME_OUT);
			conn.setReadTimeout(READ_TIME_OUT);
			conn.connect();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENC));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException e) {
			}
		}
	}

	private static String appAuth2Treaty(String appid, String appSecret) throws UnsupportedEncodingException {
		String authorization = "OpenAuth2 version=\"%s\", appid=\"%s\", timestamp=%d, nonce=\"%s\", sign=\"%s\"";
		String version = "1.1";
		appid = URLEncoder.encode(appid, ENC);
		long timestamp = System.currentTimeMillis();
		String nonce = URLEncoder.encode(UUID.randomUUID().toString(), ENC);
		String sign = shaHex(version, appid, timestamp + "", nonce, appSecret);
		sign = URLEncoder.encode(sign, ENC);
		authorization = String.format(authorization, version, appid, timestamp, nonce, sign);
		return authorization;
	}

	@SuppressWarnings("deprecation")
	private static String shaHex(String... data) {
		Arrays.sort(data);
		String join = StringUtils.join(data);
		String sign = DigestUtils.shaHex(join);
		return sign;
	}

}
