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

public abstract class ParamObtainer {

	private ParamValue<?> currentParamValue;
	private ParamValue<?> lastParamValue;

	public Param paramInfo;

	public ParamObtainer() {
	}

	public ParamValue<?> getCurrentParamValue() {

		lastParamValue = currentParamValue;
		currentParamValue = getNewValue();
		return currentParamValue;
	}

	public abstract ParamValue<?> getNewValue();

	public ParamValue<?> getLastParamValue() {
		return lastParamValue;
	}

	public String getParamName() {
		return paramInfo.getName();
	}

}
