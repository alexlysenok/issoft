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

public abstract class ParamObtainer<T extends ParamValue> {

	private T currentParamValue;
	private T lastParamValue;
	private Param paramInfo;

	public T getCurrentParamValue() {

		lastParamValue = currentParamValue;
		currentParamValue = obtainValue();
		return currentParamValue;
	}

	public abstract T obtainValue();

	public T getLastParamValue() {
		return lastParamValue;
	}

	public String getParamName() {
		return paramInfo.getName();
	}

	public Param getParamInfo() {
		return paramInfo;
	}

	public void setParamInfo(Param paramInfo) {
		this.paramInfo = paramInfo;
	}

}
