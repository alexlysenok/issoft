package org.by.issoft.paramCollector.paramObtainers;

import java.time.LocalTime;
import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;

/**
 * 
 * Represents current time obtainer class
 * 
 * @author AlexeyLysenok
 *
 */

public class CurrentTimeObtainer extends ParamObtainer {

	// use fields from super class

	// remove constructor
	public CurrentTimeObtainer() {
		paramInfo = new Param("CURRENT_TIME", ParamType.SCALAR);
	}

	// move to abstract logic
	/*
	 * @Override public TimeValue getCurrentParamValue() {
	 * 
	 * 
	 * LocalTime now = LocalTime.now(); lastParamValue = currentParamValue;
	 * currentParamValue = new TimeValue(now); return currentParamValue; }
	 */

	@Override
	public TimeValue getNewValue() {
		LocalTime now = LocalTime.now();
		return new TimeValue(now);
	}

}
