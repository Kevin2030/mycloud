package com.kingdee.internet.finance.testcase;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.kingdee.internet.finance.utils.AuthManager;
import com.kingdee.internet.finance.utils.ContextManager;

public class CloudTest {

	@Test
	public void testAuth() {
		Assert.assertNotNull(AuthManager.auth());
	}

	@Test
	public void testContext() {
		Assert.assertNotNull(ContextManager.getContext(UUID.randomUUID().toString().replaceAll("-", ""), "13123123"));
	}

}
