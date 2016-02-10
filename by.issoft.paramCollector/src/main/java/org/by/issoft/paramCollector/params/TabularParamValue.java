package org.by.issoft.paramCollector.params;

public abstract class TabularParamValue<T> extends ParamValue<T> {
	
	
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
