package com.nisovin.shopkeepers.config;

/**
 * This exception is issued by {@link Setting} if it cannot load its value.
 */
public class SettingLoadException extends Exception {

	private static final long serialVersionUID = -381457488480669740L;

	public SettingLoadException(String message) {
		super(message);
	}

	public SettingLoadException(String message, Throwable cause) {
		super(message, cause);
	}
}
