package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents Free Physical Memory param value
 * 
 * @author AlexeyLysenok
 *
 */

public class FreePhysicalMemoryValue extends ScalarParamValue<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8796715652361559994L;

	public FreePhysicalMemoryValue(Long value) {
		super(value);
	}

	@Override
	public int compareTo(ParamValue o) {
		if (o instanceof FreePhysicalMemoryValue) {
			FreePhysicalMemoryValue object = (FreePhysicalMemoryValue) o;
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
