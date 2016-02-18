package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;

/**
 * 
 * Represents param obtainer abstract class
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class ParamObtainer<T> {

	// private ParamValue<?> currentParamValue;
	// private ParamValue<?> lastParamValue;

	private T currentParamValue;
	private T lastParamValue;

	public Param paramInfo;

	public Class getGenericType() {
		return currentParamValue.getClass();
	}

	public T getCurrentParamValue() {

		lastParamValue = currentParamValue;
		currentParamValue = getNewValue();
		return currentParamValue;
	}

	public abstract T getNewValue();

	public T getLastParamValue() {
		return lastParamValue;
	}

	public String getParamName() {
		return paramInfo.getName();
	}

}
