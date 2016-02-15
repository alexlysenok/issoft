package org.by.issoft.paramCollector.paramObtainers;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamInfo;
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

	private TimeValue currentValue = new TimeValue();
	private TimeValue lastValue = new TimeValue();

	// remove constructor
	public CurrentTimeObtainer() {
		paramInfo = new ParamInfo("CURRENT_TIME", ParamType.SCALAR);
	}

	@Override
	public TimeValue getCurrentParamValue() {

		LocalTime now = LocalTime.now();
		lastValue = currentValue;
		currentValue = new TimeValue(now);
		return currentValue;
	}

	@Override
	public TimeValue getLastParamValue() {
		TimeValue time = new TimeValue(lastValue.getValue());
		return lastValue;
	}

}
