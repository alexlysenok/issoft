package org.module1.parameter;

import java.util.List;

public abstract class VectorParam<String> extends Param<List<String>>{
	
    List<String> value;
	
	@Override
	public List<String> getValue() {
		return value;
	}
	
	@Override
	public void setValue(List<String> value) {
		this.value = value;
	} 
	
	public VectorParam(){}
	
	@Override
	public void valueToString(){
		System.out.println(this.name+": ");
		for(String s:value){
			System.out.println(s);
		}
	}

}
