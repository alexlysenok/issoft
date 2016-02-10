package org.by.issoft.paramCollector.params;

public abstract class ParamValue<T> {
	
	private T value;
	
	public Param paramInfo=new Param();
	
	public ParamValue() {
		
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public Param getParamInfo() {
		return paramInfo;
	}

	public void setParamInfo(Param paramInfo) {
		this.paramInfo = paramInfo;
	}
	
}
