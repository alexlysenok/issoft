package org.by.issoft.paramCollector.params.scalarParamValues;

import java.util.Date;

import org.by.issoft.paramCollector.params.ScalarParamValue;

public class CurrentTimeValue extends ScalarParamValue<Date> {
	Date value;
	
	public CurrentTimeValue() {
		
		this.paramInfo.setName("CURRENT_TIME");
	}
	
	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public void setValue(long time) {
		// TODO Auto-generated method stub
		
	}
}
