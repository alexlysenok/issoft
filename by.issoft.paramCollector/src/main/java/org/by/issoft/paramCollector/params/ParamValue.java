package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents param value abstract class
 * 
 * @author AlexeyLysenok
 *
 */
public abstract class ParamValue<T> {

	private T value;

	public ParamValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toString() {

		return value.toString();
	}

}
