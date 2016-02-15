package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamInfo;
import org.by.issoft.paramCollector.params.ParamType;
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

	FreePhysicalMemoryValue freeMemory = new FreePhysicalMemoryValue();

	public FreePhysicalMemoryObtainer() {
		paramInfo = new ParamInfo("FREE_RAM", ParamType.SCALAR);
	}

	@Override
	public FreePhysicalMemoryValue getCurrentParamValue() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long memory = osBean.getFreePhysicalMemorySize();
		freeMemory = new FreePhysicalMemoryValue(memory);
		return freeMemory;
	}

	@Override
	public FreePhysicalMemoryValue getLastParamValue() {

		return freeMemory;
	}

}
