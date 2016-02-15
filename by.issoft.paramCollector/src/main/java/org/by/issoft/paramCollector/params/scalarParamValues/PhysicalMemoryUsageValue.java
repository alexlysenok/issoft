package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents Physical Memory Usage param value
 * 
 * @author AlexeyLysenok
 *
 */

// refactor to LongParamValue????
public class PhysicalMemoryUsageValue extends ScalarParamValue<Long> {

	public PhysicalMemoryUsageValue() {

	}

	public PhysicalMemoryUsageValue(Long value) {
		super(value);
	}

}
