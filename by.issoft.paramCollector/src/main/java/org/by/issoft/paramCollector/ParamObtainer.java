package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.params.ParamValue;

/**
 * 
 * Represents param obtainer abstract class
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class ParamObtainer {
	
	ParamValue<?> param;
	
	public ParamObtainer(){}
	
	public ParamObtainer(ParamValue<?> param){
		this.param=param;
	}
	
	public abstract ParamValue<?> getCurrentParamValue();
	
	public abstract ParamValue<?> getLastParamValue();
	
	public abstract String getParamName();
	
	
	public void print(){}
}
