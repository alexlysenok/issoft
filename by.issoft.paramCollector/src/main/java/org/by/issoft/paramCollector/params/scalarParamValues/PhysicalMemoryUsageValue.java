package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents Physical Memory Usage param value
 * 
 * @author AlexeyLysenok
 *
 */

//refactor to LongParamValue????
public class PhysicalMemoryUsageValue extends ScalarParamValue<Long>{
	
	Long value;
	
	public PhysicalMemoryUsageValue() {
		this.paramInfo.setName("PHYSICAL_MEMORY_USAGE");
	}

	public Long getValue() {
		
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
