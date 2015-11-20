package com.kingdee.internet.finance.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.internet.finance.config.ConfigConst;

public class ContextManager {

	private static final Logger	logger				= LogManager.getLogger(AuthManager.class);
	private static final int	CONNECTION_TIME_OUT	= 50000;
	private static final int	READ_TIME_OUT		= 300000;
	private static final String	ENC					= "UTF-8";
	private static final String	CONTENT_TYPE		= "content_type";

	public static String getContext(String accessToken, String ticket) {
		OutputStream os = null;
		BufferedReader br = null;
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(ConfigConst.CONSTANT.contextURL);
			HttpURLConnection.setFollowRedirects(true);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RequestMethod.POST.name());
			conn.setRequestProperty(CONTENT_TYPE, "application/x-www-form-urlencoded");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(CONNECTION_TIME_OUT);
			conn.setReadTimeout(READ_TIME_OUT);
			conn.connect();
			os = conn.getOutputStream();
			os.write(buildJSONData(accessToken, ticket).getBytes(ENC));
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
				if (os != null) os.close();
				if (br != null) br.close();
			} catch (IOException e) {
			}
		}
	}

	private static String buildJSONData(String accessToken, String ticket) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("access_token", accessToken);
		jsonObject.put("ticket", ticket);
		return jsonObject.toJSONString();
	}
}
