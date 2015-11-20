package com.kingdee.internet.finance.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ConfigManager {

	SYSTEMCONFIG;

	private Properties		prop		= null;
	private final String	propName	= "kdweibo.properties";
	private final Logger	logger		= LogManager.getLogger(ConfigManager.class);

	private ConfigManager() {
		InputStreamReader inStream = null;
		try {
			inStream = new InputStreamReader(ConfigManager.class.getClassLoader().getResourceAsStream(propName));
			prop = new Properties();
			prop.load(inStream);
			logger.info("Load properties[{}] success!", propName);
		} catch (IOException e) {
			logger.error("Load properties[{}] failure! {}", propName, e);
		} finally {
			try {
				if (inStream != null) inStream.close();
			} catch (Exception ex) {
				logger.warn(ex.getMessage(), ex);
			}
		}
	}

	public Integer getIntegerPropties(final String key) {
		return prop.getProperty(key) == null ? null : Integer.parseInt(prop.getProperty(key));
	}

	public Integer getIntegerPropties(final String key, final int defaultValue) {
		return getIntegerPropties(key) == null ? defaultValue : getIntegerPropties(key);
	}

	public Long getLongPropties(final String key) {
		return prop.getProperty(key) == null ? null : Long.parseLong(prop.getProperty(key));
	}

	public Long getLongPropties(final String key, final long defaultValue) {
		return getLongPropties(key) == null ? defaultValue : getLongPropties(key);
	}

	public Boolean getBooleanPropties(final String key) {
		return prop.getProperty(key) == null ? null : Boolean.parseBoolean(prop.getProperty(key));
	}

	public Boolean getBooleanPropties(final String key, final boolean defaultValue) {
		return getBooleanPropties(key) == null ? defaultValue : getBooleanPropties(key);
	}

	public String getStringPropties(final String key) {
		return prop.getProperty(key);
	}

	public String getStringPropties(final String key, final String defaultValue) {
		return getStringPropties(key) == null ? defaultValue : getStringPropties(key);
	}

}
