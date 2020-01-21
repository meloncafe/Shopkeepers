package com.nisovin.shopkeepers.config;

import java.util.function.Supplier;

import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.shopkeepers.util.Validate;

public abstract class Setting<T> {

	private final String path;
	private final Supplier<T> defaultSupplier;
	// TODO not actually supported yet
	private String comment = null; // null indicates 'no comment'
	private boolean closed = false;

	public Setting(String path, T defaultValue) {
		this(path, () -> defaultValue);
	}

	public Setting(String path, Supplier<T> defaultSupplier) {
		Validate.notEmpty(path, "Path is null or empty!");
		this.path = path;
		this.defaultSupplier = (defaultSupplier != null) ? defaultSupplier : (() -> null);
	}

	// CLOSE STATE

	/**
	 * Throws an {@link IllegalStateException} if this setting is closed.
	 * 
	 * @see #isClosed()
	 */
	protected final void validateNotClosed() {
		Validate.State.isTrue(!closed, "The setting has already been closed!");
	}

	/**
	 * Checks whether this Setting has already been {@link #close() closed}.
	 * 
	 * @return <code>true</code> if closed
	 */
	public final boolean isClosed() {
		return closed;
	}

	/**
	 * Closes this Setting for further modifications.
	 * <p>
	 * Once closed, the Setting can no longer be altered.
	 * 
	 * @param <S>
	 *            the type of this Setting
	 * @return this Setting
	 */
	@SuppressWarnings("unchecked")
	public final <S extends Setting<T>> S close() {
		this.validateNotClosed();
		closed = true;
		return (S) this;
	}

	// PROPERTIES

	/**
	 * Gets the Setting's path.
	 * 
	 * @return the Setting's path, not <code>null</code> or empty
	 */
	public final String getPath() {
		return path;
	}

	/**
	 * Gets the Setting's default value supplier.
	 * 
	 * @return the Setting's default value supplier, not <code>null</code>
	 */
	public final Supplier<T> getDefaultSupplier() {
		return defaultSupplier;
	}

	/**
	 * Gets the comment.
	 * 
	 * @return the comment, or <code>null</code> if no comment has been set
	 */
	public final String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param <S>
	 *            the type of this Setting
	 * @param comment
	 *            the new comment, can be <code>null</code> to unset any comment
	 * @return this Setting
	 */
	@SuppressWarnings("unchecked")
	public final <S extends Setting<T>> S comment(String comment) {
		this.validateNotClosed();
		this.comment = comment;
		return (S) this;
	}

	// LOADING

	/**
	 * Converts the given data object to a value for this setting.
	 * 
	 * @param dataObject
	 *            the dataObject, possibly <code>null</code>
	 * @return the setting's value, or <code>null</code> if the data object is <code>null</code>
	 * @throws SettingLoadException
	 *             if the setting's value could not be deserialized
	 */
	public abstract T deserialize(Object dataObject) throws SettingLoadException;

	/**
	 * Loads a value for this setting from the given configuration.
	 * 
	 * @param config
	 *            the configuration
	 * @return the loaded value, or <code>null</code> if there is no data for this setting
	 * @throws SettingLoadException
	 *             if the data for this setting could not be loaded
	 */
	public T loadValue(ConfigurationSection config) throws SettingLoadException {
		Validate.notNull(config, "Config is null!");
		Object dataObject = config.get(path);
		if (dataObject == null) return null;
		return this.deserialize(dataObject);
	}

	// SAVING

	/**
	 * Converts the given setting's value to a data object that can be serialized in a {@link ConfigurationSection}.
	 * 
	 * @param value
	 *            the setting's value, possibly <code>null</code>
	 * @return the serialized setting's value, or <code>null</code> if the setting's value is <code>null</code>
	 */
	public abstract Object serialize(T value);

	public void saveValue(ConfigurationSection config, T value) {
		Validate.notNull(config, "Config is null!");
		Object dataObject = this.serialize(value);
		config.set(path, dataObject);
	}

	// OTHER UTILITIES

	/**
	 * Formats the given setting's value to a (user-friendly) string.
	 * <p>
	 * By default this calls {@link String#valueOf(Object)} for the given value. Settings are supposed to override this
	 * to produce more user-friendly strings if possible.
	 * 
	 * @param value
	 *            the value
	 * @return the formatted value
	 */
	public String format(T value) {
		return String.valueOf(value);
	}

	/**
	 * Tries to parse the setting's value from the given string.
	 * <p>
	 * Settings have to override this if they support parsing from string.
	 * 
	 * @param input
	 *            the input string
	 * @return the parsed value, or <code>null</code> if the value could not be parsed or parsing is not supported
	 */
	public T parseValue(String input) {
		return null;
	}

	// JAVA OBJECT: comparison is done by identity

	@Override
	public final boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public final int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" [path=");
		builder.append(path);
		builder.append("]");
		return builder.toString();
	}
}
