package com.kingdee.internet.finance.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.internet.finance.config.CommonConstant;

public class MessageHandle {

	public static String buildResult(int status, String msg, Object data) {
		JSONObject json = new JSONObject();
		json.put(CommonConstant.STATUS, status);
		json.put(CommonConstant.MSG, msg);
		json.put(CommonConstant.DATA, data);
		return json.toJSONString();
	}

	public static String buildResult(int status, String msg, String key, Object value) {
		JSONObject json = new JSONObject();
		json.put(CommonConstant.STATUS, status);
		json.put(CommonConstant.MSG, msg);
		JSONObject result = new JSONObject();
		result.put(key, value);
		json.put(CommonConstant.DATA, result);
		return json.toJSONString();
	}

	public static String buildResult(int status, String msg, HashMap<String, Object> map) {
		JSONObject json = new JSONObject();
		json.put(CommonConstant.STATUS, status);
		json.put(CommonConstant.MSG, msg);
		JSONObject result = new JSONObject();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> obj = it.next();
			result.put(obj.getKey(), obj.getValue());
		}
		json.put(CommonConstant.DATA, result);
		return json.toJSONString();
	}

	public static String buildResult(int status, String msg) {
		JSONObject json = new JSONObject();
		json.put(CommonConstant.STATUS, status);
		json.put(CommonConstant.MSG, msg);
		return json.toJSONString();
	}
}
