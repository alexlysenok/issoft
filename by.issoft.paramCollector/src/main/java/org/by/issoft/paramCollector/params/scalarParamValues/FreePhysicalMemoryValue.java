package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * 
 * Represents Free Physical Memory param value
 * 
 * @author AlexeyLysenok
 *
 */

public class FreePhysicalMemoryValue extends ScalarParamValue<Long>{
	
	Long value;
	
	public FreePhysicalMemoryValue() {
		this.paramInfo.setName("FREE_PHYSICAL_MEMORY");
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
