package com.nisovin.shopkeepers.config;

/**
 * This exception is issued by {@link Setting} if it encounters an invalid value.
 */
public class SettingInvalidException extends Exception {

	private static final long serialVersionUID = -381457488480669740L;

	public SettingInvalidException(String message) {
		super(message);
	}

	public SettingInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
}
