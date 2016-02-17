package org.by.issoft.paramCollector.params.scalarParamValues;

import java.time.LocalTime;

/**
 * 
 * Represents current time value
 * 
 * @author AlexeyLysenok
 *
 */

import org.by.issoft.paramCollector.params.ScalarParamValue;

public class TimeValue extends ScalarParamValue<LocalTime> {

	public TimeValue(LocalTime value) {
		super(value);
	}

}
