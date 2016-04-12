package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.scalarParamValues.FreePhysicalMemoryValue;
import org.springframework.stereotype.Component;

import com.sun.management.OperatingSystemMXBean;

import org.by.issoft.paramCollector.ParamObtainer;

/**
 * 
 * Represents Free Physical Memory obtainer class
 * 
 * @author AlexeyLysenok
 *
 */
@Component
public class FreePhysicalMemoryObtainer extends ParamObtainer<FreePhysicalMemoryValue> {

	public FreePhysicalMemoryObtainer() {

		setParamInfo(new Param("FREE_RAM", ParamType.SCALAR, super.getEntityClass()));
	}

	// @Override
	// public FreePhysicalMemoryValue getCurrentParamValue() {
	// OperatingSystemMXBean osBean = (OperatingSystemMXBean)
	// ManagementFactory.getOperatingSystemMXBean();
	// long memory = osBean.getFreePhysicalMemorySize();
	// lastParamValue = currentParamValue;
	// currentParamValue = new FreePhysicalMemoryValue(memory);
	// return currentParamValue;
	// }

	@Override
	public FreePhysicalMemoryValue obtainValue() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long memory = osBean.getFreePhysicalMemorySize();
		return new FreePhysicalMemoryValue(memory);
	}

}
