package com.nisovin.shopkeepers.config.settings;

import java.util.function.Supplier;

import com.nisovin.shopkeepers.config.Setting;
import com.nisovin.shopkeepers.config.SettingLoadException;
import com.nisovin.shopkeepers.config.SettingSerialization;
import com.nisovin.shopkeepers.util.ConversionUtils;

public class IntegerSetting extends Setting<Integer> {

	public IntegerSetting(String path, Integer defaultValue) {
		super(path, defaultValue);
	}

	public IntegerSetting(String path, Supplier<Integer> defaultSupplier) {
		super(path, defaultSupplier);
	}

	@Override
	public Integer deserialize(Object dataObject) throws SettingLoadException {
		if (dataObject == null) return null;
		return SettingSerialization.requireInt(dataObject);
	}

	@Override
	public Object serialize(Integer value) {
		return value;
	}

	@Override
	public Integer parseValue(String input) {
		if (input == null) return null;
		return ConversionUtils.parseInt(input.trim());
	}
}
