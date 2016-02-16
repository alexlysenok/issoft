package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents scalar type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class ScalarParamValue<T> extends ParamValue<T> {

	public ScalarParamValue(T value) {
		super(value);
	}

}
