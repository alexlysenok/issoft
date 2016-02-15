package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.params.ParamInfo;
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

	public ParamInfo paramInfo;

	public ParamObtainer() {
	}

	public abstract ParamValue<?> getCurrentParamValue();

	public abstract ParamValue<?> getLastParamValue();

	public String getParamName() {
		return paramInfo.getName();
	}

}
