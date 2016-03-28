package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents Physical Memory Usage param value
 * 
 * @author AlexeyLysenok
 *
 */

public class PhysicalMemoryUsageValue extends ScalarParamValue<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5933233297975114549L;

	public PhysicalMemoryUsageValue(Long value) {
		super(value);
	}

	@Override
	public int compareTo(ParamValue o) {
		if (o instanceof PhysicalMemoryUsageValue) {
			PhysicalMemoryUsageValue object = (PhysicalMemoryUsageValue) o;
			return getValue().compareTo(object.getValue());
		} else {
			throw new ClassCastException();
		}
	}

	@Override
	public Long getLongValue() {
		Long double1 = new Long(getValue());
		return double1;
	}

}
