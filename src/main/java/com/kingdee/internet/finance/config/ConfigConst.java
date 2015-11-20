package com.kingdee.internet.finance.config;

import com.kingdee.internet.finance.utils.ConfigManager;

public enum ConfigConst {

	CONSTANT;

	public String	appID;
	public String	appSecret;
	public String	authURL;
	public String	contextURL;

	private ConfigConst() {
		appID = ConfigManager.SYSTEMCONFIG.getStringPropties("kdweibo.auth.appid", Default.appID);
		appSecret = ConfigManager.SYSTEMCONFIG.getStringPropties("kdweibo.auth.appSecret", Default.appSecret);
		authURL = ConfigManager.SYSTEMCONFIG.getStringPropties("kdweibo.auth.url", Default.authURL);
		contextURL = ConfigManager.SYSTEMCONFIG.getStringPropties("kdweibo.context.url", Default.contextURL);

	}

	private static class Default {
		public static final String	appID		= "test";
		public static final String	appSecret	= "test";
		public static final String	authURL		= "http://do.kdweibo.com/openauth2/api/appAuth2";
		public static final String	contextURL	= "http://do.kdweibo.com/openauth2/api/getcontext";
	}

}
