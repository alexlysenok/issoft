package org.by.issoft.paramCollector.params;

/**
 * 
 * Represents vector type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class VectorParamValue<T> extends ParamValue<T>{

	// implement abstract vector logic
	
	public VectorParamValue() {
		this.paramInfo.setType("VectorParam");
	}
	
	
}
