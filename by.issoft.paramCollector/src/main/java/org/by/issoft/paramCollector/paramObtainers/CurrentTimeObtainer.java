package org.by.issoft.paramCollector.paramObtainers;

import java.time.Instant;
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

public class CurrentTimeObtainer extends ParamObtainer<TimeValue> {

	public CurrentTimeObtainer() {
		paramInfo = new Param("CURRENT_TIME", ParamType.SCALAR);
	}

	@Override
	public TimeValue obtainValue() {
		Instant now = Instant.now();

		return new TimeValue(now);
	}

}
