package com.nisovin.shopkeepers.config;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.shopkeepers.util.ConversionUtils;
import com.nisovin.shopkeepers.util.StringUtils;

/**
 * Utilities related to (de-)serialization of setting values.
 */
public class SettingSerialization {

	private SettingSerialization() {
	}

	private static <T> T requireType(Function<Object, T> converter, Object dataObject, String defaultErrorMsg, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		T value = converter.apply(dataObject);
		if (value == null) {
			String errorMessage = (errorMessageSupplier != null) ? errorMessageSupplier.get() : null;
			if (StringUtils.isEmpty(errorMessage)) {
				errorMessage = defaultErrorMsg;
			}
			throw new SettingLoadException(errorMessage);
		}
		return value;
	}

	private static <T> T requireType(Class<T> type, Object dataObject, String defaultErrorMsg, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType((data) -> type.isInstance(data) ? type.cast(data) : null, dataObject, defaultErrorMsg, errorMessageSupplier);
	}

	// config section

	/**
	 * Casts the given object to a {@link ConfigurationSection}.
	 * 
	 * @param dataObject
	 *            the data object
	 * @return the config section
	 * @throws SettingLoadException
	 *             if the given object is not a {@link ConfigurationSection}
	 */
	public static ConfigurationSection requireConfigSection(Object dataObject) throws SettingLoadException {
		return requireConfigSection(dataObject, (Supplier<String>) null);
	}

	public static ConfigurationSection requireConfigSection(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireConfigSection(dataObject, () -> errorMessage);
	}

	public static ConfigurationSection requireConfigSection(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType(ConfigurationSection.class, dataObject, "Data is not a config section!", errorMessageSupplier);
	}

	public static ConfigurationSection getConfigSection(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireConfigSection(value, () -> "Could not get config section at path: " + path);
	}

	// list

	/**
	 * Casts the given object to a {@link List}.
	 * 
	 * @param dataObject
	 *            the data object
	 * @return the list
	 * @throws SettingLoadException
	 *             if the given object is not a {@link List}
	 */
	public static List<?> requireList(Object dataObject) throws SettingLoadException {
		return requireList(dataObject, (Supplier<String>) null);
	}

	public static List<?> requireList(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireList(dataObject, () -> errorMessage);
	}

	public static List<?> requireList(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType(List.class, dataObject, "Data is not a list!", errorMessageSupplier);
	}

	public static List<?> getList(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireList(value, () -> "Could not get list at path: " + path);
	}

	// boolean

	public static boolean requireBoolean(Object dataObject) throws SettingLoadException {
		return requireBoolean(dataObject, (Supplier<String>) null);
	}

	public static boolean requireBoolean(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireBoolean(dataObject, () -> errorMessage);
	}

	public static boolean requireBoolean(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType((data) -> ConversionUtils.toBoolean(data), dataObject, "Data is not a boolean!", errorMessageSupplier);
	}

	public static boolean getBoolean(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireBoolean(value, () -> "Could not get boolean at path: " + path);
	}

	// double

	public static double requireDouble(Object dataObject) throws SettingLoadException {
		return requireDouble(dataObject, (Supplier<String>) null);
	}

	public static double requireDouble(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireDouble(dataObject, () -> errorMessage);
	}

	public static double requireDouble(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType((data) -> ConversionUtils.toDouble(data), dataObject, "Data is not a double!", errorMessageSupplier);
	}

	public static double getDouble(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireDouble(value, () -> "Could not get double at path: " + path);
	}

	// float

	public static float requireFloat(Object dataObject) throws SettingLoadException {
		return requireFloat(dataObject, (Supplier<String>) null);
	}

	public static float requireFloat(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireFloat(dataObject, () -> errorMessage);
	}

	public static float requireFloat(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType((data) -> ConversionUtils.toFloat(data), dataObject, "Data is not a float!", errorMessageSupplier);
	}

	public static float getFloat(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireFloat(value, () -> "Could not get float at path: " + path);
	}

	// int

	public static int requireInt(Object dataObject) throws SettingLoadException {
		return requireInt(dataObject, (Supplier<String>) null);
	}

	public static int requireInt(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireInt(dataObject, () -> errorMessage);
	}

	public static int requireInt(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType((data) -> ConversionUtils.toInteger(data), dataObject, "Data is not an integer!", errorMessageSupplier);
	}

	public static int getInt(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireInt(value, () -> "Could not get integer at path: " + path);
	}

	// long

	public static long requireLong(Object dataObject) throws SettingLoadException {
		return requireLong(dataObject, (Supplier<String>) null);
	}

	public static long requireLong(Object dataObject, String errorMessage) throws SettingLoadException {
		return requireLong(dataObject, () -> errorMessage);
	}

	public static long requireLong(Object dataObject, Supplier<String> errorMessageSupplier) throws SettingLoadException {
		return requireType((data) -> ConversionUtils.toLong(data), dataObject, "Data is not a long!", errorMessageSupplier);
	}

	public static long getLong(ConfigurationSection config, String path) throws SettingLoadException {
		Object value = config.get(path);
		return requireLong(value, () -> "Could not get long at path: " + path);
	}
}
