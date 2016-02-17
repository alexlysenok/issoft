package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.ParamValue;
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

	public PhysicalMemoryUsageObtainer() {
		paramInfo = new Param("USING_RAM", ParamType.SCALAR);
	}

	@Override
	public ParamValue<?> getNewValue() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long memory = (long) (osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize());
		return new PhysicalMemoryUsageValue(memory);
	}

}
