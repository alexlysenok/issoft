package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.params.ParamValue;

public class ParamWrapper {

	private ParamValue<?> paramValue;

	public ParamValue<?> getParamValue() {
		return paramValue;
	}

	public void setParamValue(ParamValue<?> paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public String toString() {
		return paramValue.toString();
	}
}
