package com.nisovin.shopkeepers.config.settings;

import java.util.function.Supplier;

import com.nisovin.shopkeepers.config.Setting;
import com.nisovin.shopkeepers.config.SettingLoadException;
import com.nisovin.shopkeepers.config.SettingSerialization;
import com.nisovin.shopkeepers.util.ConversionUtils;

public class BooleanSetting extends Setting<Boolean> {

	public BooleanSetting(String path, Boolean defaultValue) {
		super(path, defaultValue);
	}

	public BooleanSetting(String path, Supplier<Boolean> defaultSupplier) {
		super(path, defaultSupplier);
	}

	@Override
	public Boolean deserialize(Object dataObject) throws SettingLoadException {
		if (dataObject == null) return null;
		return SettingSerialization.requireBoolean(dataObject);
	}

	@Override
	public Object serialize(Boolean value) {
		return value;
	}

	@Override
	public Boolean parseValue(String input) {
		if (input == null) return null;
		return ConversionUtils.parseBoolean(input.trim());
	}
}
