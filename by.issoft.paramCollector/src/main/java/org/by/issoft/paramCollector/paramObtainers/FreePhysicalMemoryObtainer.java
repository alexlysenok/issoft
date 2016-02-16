package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.FreePhysicalMemoryValue;

import com.sun.management.OperatingSystemMXBean;

/**
 * 
 * Represents Free Physical Memory obtainer class
 * 
 * @author AlexeyLysenok
 *
 */

public class FreePhysicalMemoryObtainer extends ParamObtainer {

	FreePhysicalMemoryValue currentValue;
	FreePhysicalMemoryValue lastValue;

	public FreePhysicalMemoryObtainer() {
		paramInfo = new Param("FREE_RAM", ParamType.SCALAR);
	}

	@Override
	public FreePhysicalMemoryValue getCurrentParamValue() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long memory = osBean.getFreePhysicalMemorySize();
		lastValue = currentValue;
		currentValue = new FreePhysicalMemoryValue(memory);
		return currentValue;
	}

	@Override
	public FreePhysicalMemoryValue getLastParamValue() {
		lastValue = new FreePhysicalMemoryValue(lastValue.getValue());
		return lastValue;
	}

	@Override
	public ParamValue<?> getNewValue() {
		return null;
	}

}
