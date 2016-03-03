package org.by.issoft.paramCollector.params.scalarParamValues;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.by.issoft.paramCollector.params.ParamValue;

/**
 * 
 * Represents current time value
 * 
 * @author AlexeyLysenok
 *
 */

import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents time value class
 * 
 * @author AlexeyLysenok
 *
 **/

public class TimeValue extends ScalarParamValue<Long> {

	public TimeValue(Long value) {
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
	public Long getLongValue() {

		return getValue();
	}

	@Override
	public String toString() {

		Date date = new Date(getValue());
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Etc/GMT-3"));
		return format.format(date);

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

	public static void main(String[] args) {

		Instant instant = Instant.now();
		System.out.println(instant.toEpochMilli());

	}

}
