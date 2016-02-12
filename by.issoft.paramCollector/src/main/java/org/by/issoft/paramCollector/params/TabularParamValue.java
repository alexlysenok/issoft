package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents tabular type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class TabularParamValue<T> extends ParamValue<T> {
	
	// implement abstract table logic
	T value;
	
	public TabularParamValue() {
		this.paramInfo.setType("TabularParam");
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
}
