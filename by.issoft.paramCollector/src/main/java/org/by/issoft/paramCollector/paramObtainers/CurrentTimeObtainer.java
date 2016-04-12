package org.by.issoft.paramCollector.paramObtainers;

import java.time.Instant;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;
import org.springframework.stereotype.Component;

import org.by.issoft.paramCollector.ParamObtainer;

/**
 * 
 * Represents current time obtainer class
 * 
 * @author AlexeyLysenok
 *
 */
@Component
// @Quilified(CurrentTimeObtainer.NAME)
public class CurrentTimeObtainer extends ParamObtainer<TimeValue> {

	public static String NAME = "CURRENT_TIME";

	public CurrentTimeObtainer() {
		setParamInfo(new Param("CURRENT_TIME", ParamType.SCALAR, super.getEntityClass()));
	}

	@Override
	public TimeValue obtainValue() {

		return new TimeValue(new Long(Instant.now().toEpochMilli()));
	}

}
