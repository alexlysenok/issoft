package org.by.issoft.paramCollector.paramObtainers;

import java.time.Instant;
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
		setParamInfo(new Param("CURRENT_TIME", ParamType.SCALAR, super.getEntityClass()));
	}

	@Override
	public TimeValue obtainValue() {

		return new TimeValue(new Long(Instant.now().toEpochMilli()));
	}

}
