package org.module1.parameter;

public abstract class ScalarParam extends Param {
	protected String value;
	
	public ScalarParam(){
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
