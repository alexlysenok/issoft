package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.Param;
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

public class PhysicalMemoryUsageObtainer extends ParamObtainer<PhysicalMemoryUsageValue> {

	public PhysicalMemoryUsageObtainer() {
		setParamInfo(new Param("USING_RAM", ParamType.SCALAR));
	}

	@Override
	public PhysicalMemoryUsageValue obtainValue() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long memory = (long) (osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize());
		return new PhysicalMemoryUsageValue(memory);
	}

}
