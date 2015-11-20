package com.kingdee.internet.finance.utils;

import com.alibaba.fastjson.JSONObject;

public class AuthUtil {

	public static <T> T getData(String result, Class<T> clazz) {
		return JSONObject.parseObject(JSONObject.parseObject(result).toString(), clazz);
	}

	public static int getErrorCode(String result) {
		return JSONObject.parseObject(result).getIntValue("errorCode");
	}

	public static boolean isSuccess(String result) {
		return JSONObject.parseObject(result).getBooleanValue("success");
	}

	public static String getError(String result) {
		return JSONObject.parseObject(result).getString("error");
	}

	public static String getAccessToken(String result) {
		return JSONObject.parseObject(result).getJSONObject("data").getString("access_token");
	}

}
