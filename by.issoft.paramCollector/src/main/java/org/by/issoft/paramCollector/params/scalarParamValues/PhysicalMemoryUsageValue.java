package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents Physical Memory Usage param value
 * 
 * @author AlexeyLysenok
 *
 */

public class PhysicalMemoryUsageValue extends ScalarParamValue<Long> implements Comparable {

	public PhysicalMemoryUsageValue(Long value) {
		super(value);
	}

	@Override
	public int compareTo(Object o) {
		int result = this.compareTo(o);
		return result;
	}

}
