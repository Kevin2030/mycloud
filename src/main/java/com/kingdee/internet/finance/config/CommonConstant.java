package com.kingdee.internet.finance.config;

public class CommonConstant {

	public static final String	STATUS	= "status";
	public static final String	MSG		= "msg";
	public static final String	DATA	= "data";

	public class ReturnMessage {

		public static final String	SUCCESS				= "操作成功";
		public static final String	FAILURE				= "操作失败";
		public static final String	SERVICE_ERROR		= "服务器异常,请稍候再试!";
		public static final String	CLIENT_INPUT_ERROR	= "客户端输入错误";
		public static final String	REQUEST_ILLEGAL		= "请求非法";
		public static final String	SERVICE_TIME_OUT	= "请求超时";
	}

	public class ReturnCode {
		public static final int	SUCCESS				= 200;
		public static final int	SERVICE_ERROR		= 500;
		public static final int	REQUEST_ILLEGAL		= 501;
		public static final int	SERVICE_TIME_OUT	= 502;
		public static final int	FAILURE				= 600;
		public static final int	CLIENT_INPUT_ERROR	= 601;
	}

}
