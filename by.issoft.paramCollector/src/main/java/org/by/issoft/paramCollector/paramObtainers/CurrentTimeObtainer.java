package org.by.issoft.paramCollector.paramObtainers;

import java.util.Date;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.scalarParamValues.CurrentTimeValue;

/**
 * 
 * Represents current time obtainer class
 * 
 * @author AlexeyLysenok
 *
 */

public class CurrentTimeObtainer extends ParamObtainer {

	private CurrentTimeValue time = new CurrentTimeValue();

	//remove constructor
	public CurrentTimeObtainer() {
	}

	@Override
	public CurrentTimeValue getCurrentParamValue() {
		
		//1. make value immutable
		//2. use java 8 LocalTime 
		time.setValue(new Date());
		return time;
	}

	//change this logic to return previous fetched value (previoud getCurrentParamValue call)
	@Override
	public CurrentTimeValue getLastParamValue() {
		if (time.getValue() == null) {
			time = getCurrentParamValue();
		}
		return time;
	}

	//mave param name to obtainer????
	@Override
	public String getParamName() {
		return time.paramInfo.getName();
	}

	//change this method to return String instead of printing
	@Override
	public void print() {
		System.out.println("Current time:");
		
		//do not use depricate method
		System.out.println(time.getValue().getHours() + " : " + time.getValue().getMinutes());

	}

	/*
	 * 
	 * public class CurrentTimeInner extends ScalarParamValue<Date>{ Date value;
	 * 
	 * public CurrentTimeInner() { this.paramInfo.setName("CURRENT_TIME"); }
	 * 
	 * public Date getValue() { return value; }
	 * 
	 * public void setValue(Date value) { this.value = value; } }
	 */

}
