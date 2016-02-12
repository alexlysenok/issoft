package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents scalar type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class ScalarParamValue<T> extends ParamValue<T> {
	
	
	//make type as enum
	public ScalarParamValue(){
		this.paramInfo.setType("ScalarParam");
	}

	
}
