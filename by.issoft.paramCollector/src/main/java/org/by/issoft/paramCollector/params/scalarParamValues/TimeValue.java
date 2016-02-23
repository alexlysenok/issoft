package org.by.issoft.paramCollector.params.scalarParamValues;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

import org.by.issoft.paramCollector.params.ParamValue;

/**
 * 
 * Represents current time value
 * 
 * @author AlexeyLysenok
 *
 */

import org.by.issoft.paramCollector.params.ScalarParamValue;

public class TimeValue extends ScalarParamValue<Instant> {

	public TimeValue(Instant value) {
		super(value);
	}

	@Override
	public int compareTo(ParamValue o) {
		if (o instanceof TimeValue) {
			TimeValue object = (TimeValue) o;
			return getValue().compareTo(object.getValue());
		} else {
			throw new ClassCastException();
		}
	}

	@Override
	public Long getDoubleValue() {

		Long double1 = new Long(getValue().getEpochSecond());
		return double1;
	}

	// @Override
	// public ParamValue summarize(ParamValue o) {
	// if (o instanceof TimeValue) {
	// TimeValue object = (TimeValue) o;
	// // LocalTime result = getValue().ge + o.getValue();
	// return null;
	// } else {
	// throw new ClassCastException();
	// }
	// }

}
