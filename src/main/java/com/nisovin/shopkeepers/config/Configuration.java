package com.nisovin.shopkeepers.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.shopkeepers.util.Validate;

public class Configuration {

	private final Map<Setting<?>, Object> values = new LinkedHashMap<>();

	public Configuration() {
	}

	/**
	 * Gets the value for the given {@link Setting}.
	 * 
	 * @param <T>
	 *            the setting's type
	 * @param setting
	 *            the setting
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Setting<T> setting) {
		return (T) values.get(setting);
	}

	/**
	 * Sets the value for the given {@link Setting}.
	 * <p>
	 * This replaces any previously stored value for the given setting.
	 * 
	 * @param <T>
	 *            the setting's type
	 * @param setting
	 *            the setting
	 * @param value
	 *            the new value, or <code>null</code> to unset any value for the setting
	 */
	public <T> void set(Setting<T> setting, T value) {
		Validate.notNull(setting, "Setting is null!");
		if (value == null) {
			values.remove(setting);
		} else {
			values.put(setting, value);
		}
	}

	// LOADING

	/**
	 * Clears the values for all settings.
	 */
	public void clear() {
		this.values.clear();
	}

	/**
	 * Loads the values for the given settings.
	 * <p>
	 * This replaces the current values for these settings. The values for any other settings are unaffected unless
	 * {@link #clear()} is called before.
	 * 
	 * @param config
	 *            the config to load the values from
	 * @param settings
	 *            the settings to load
	 * @throws ConfigLoadException
	 *             if some setting could not be loaded
	 */
	public void loadSettings(ConfigurationSection config, Iterable<Setting<?>> settings) throws ConfigLoadException {
		Validate.notNull(config, "Config is null!");
		Validate.notNull(settings, "Settings is null!");
		for (Setting<?> setting : settings) {
			if (setting == null) continue; // skip invalid settings
			try {
				this.loadSetting(config, setting);
			} catch (SettingLoadException e) {
				throw new ConfigLoadException("Could not load setting at path: " + setting.getPath(), e);
			}
		}
	}

	private <T> void loadSetting(ConfigurationSection config, Setting<T> setting) throws SettingLoadException {
		assert config != null && setting != null;
		T value = setting.loadValue(config);
		this.set(setting, value);
	}

	// SAVING

	/**
	 * Saves the setting values to the given {@link ConfigurationSection}.
	 * 
	 * @param config
	 *            the config to save the values to
	 */
	public void save(ConfigurationSection config) {
		Validate.notNull(config, "Config is null!");
		for (Entry<Setting<?>, Object> entry : values.entrySet()) {
			Setting<?> setting = entry.getKey();
			Object value = entry.getValue();
			this.saveSetting(config, setting, value);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> void saveSetting(ConfigurationSection config, Setting<T> setting, Object value) {
		assert config != null && setting != null && value != null;
		setting.saveValue(config, (T) value);
	}

	// JAVA OBJECT

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + values.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Configuration)) return false;
		Configuration other = (Configuration) obj;
		if (!values.equals(other.values)) return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Configuration [values=");
		builder.append(values);
		builder.append("]");
		return builder.toString();
	}
}
