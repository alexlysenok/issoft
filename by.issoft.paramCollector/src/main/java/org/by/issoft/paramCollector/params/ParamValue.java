package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents param value abstract class
 * 
 * @author AlexeyLysenok
 *
 */
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
