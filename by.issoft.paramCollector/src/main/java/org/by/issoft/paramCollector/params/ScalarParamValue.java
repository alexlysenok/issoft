package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents scalar type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class ScalarParamValue<T> extends ParamValueAbstract<T> {

	public ScalarParamValue(T value) {
		super(value);
	}

	@Override
	public String toString() {

		return getValue().toString();
	}

	public abstract Long getLongValue();

}
