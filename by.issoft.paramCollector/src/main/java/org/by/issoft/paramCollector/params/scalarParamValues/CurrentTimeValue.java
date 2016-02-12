package org.by.issoft.paramCollector.params.scalarParamValues;

/**
 * 
 * Represents current time value
 * 
 * @author AlexeyLysenok
 *
 */

import java.util.Date;

import org.by.issoft.paramCollector.params.ScalarParamValue;

public class CurrentTimeValue extends ScalarParamValue<Date> {
	//use value in super class
	Date value;
	
	//remove
	public CurrentTimeValue() {
		
		this.paramInfo.setName("CURRENT_TIME");
	}
	
	public Date getValue() {
		return value;
	}

	//make immutable
	public void setValue(Date value) {
		this.value = value;
	}

	
}
