package org.module1.parameter;

import java.util.List;



public abstract class ScalarParam extends Param<String> {
	
	String value;
	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public void setValue(String value) {
		this.value = value;
	} 
	
	public ScalarParam(){
		
	}
	@Override
	public void valueToString(){
		System.out.println(this.name+": "+value);
	}


}
