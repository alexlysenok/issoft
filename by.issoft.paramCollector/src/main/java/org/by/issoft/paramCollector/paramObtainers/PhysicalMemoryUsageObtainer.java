package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamInfo;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;

import com.sun.management.OperatingSystemMXBean;

/**
 * 
 * Represents Physical Memory Usage Value obtainer class
 * 
 * @author AlexeyLysenok
 *
 */

public class PhysicalMemoryUsageObtainer extends ParamObtainer {

	PhysicalMemoryUsageValue currentValue = new PhysicalMemoryUsageValue();
	PhysicalMemoryUsageValue lastValue = new PhysicalMemoryUsageValue();

	public PhysicalMemoryUsageObtainer() {
		paramInfo = new ParamInfo("USING_RAM", ParamType.SCALAR);
	}

	@Override
	public PhysicalMemoryUsageValue getCurrentParamValue() {

		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long memory = (long) (osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize());
		lastValue = currentValue;
		currentValue = new PhysicalMemoryUsageValue(memory);
		return currentValue;
	}

	@Override
	public PhysicalMemoryUsageValue getLastParamValue() {
		lastValue = new PhysicalMemoryUsageValue(lastValue.getValue());
		return lastValue;
	}

}
