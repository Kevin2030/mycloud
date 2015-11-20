package com.kingdee.internet.finance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingdee.internet.finance.config.CommonConstant;
import com.kingdee.internet.finance.utils.AuthManager;
import com.kingdee.internet.finance.utils.AuthUtil;
import com.kingdee.internet.finance.utils.MessageHandle;

@Controller
@RequestMapping(value = "cloud", produces = "application/json;charset=utf-8")
public class CloudController {

	private static final Logger	logger	= LogManager.getLogger(CloudController.class);

	@RequestMapping(value = "openAuth", method = RequestMethod.GET)
	@ResponseBody
	public String openAuth(HttpServletRequest request, HttpServletResponse response) {
		try {
			String result = AuthManager.auth();
			if (!AuthUtil.isSuccess(result)) {
				return MessageHandle.buildResult(CommonConstant.ReturnCode.FAILURE, AuthUtil.getError(result));
			}
			return MessageHandle.buildResult(CommonConstant.ReturnCode.SUCCESS, CommonConstant.ReturnMessage.SUCCESS,
					AuthUtil.getAccessToken(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return MessageHandle.buildResult(CommonConstant.ReturnCode.SERVICE_ERROR,
					CommonConstant.ReturnMessage.SERVICE_ERROR);
		}
	}

}
