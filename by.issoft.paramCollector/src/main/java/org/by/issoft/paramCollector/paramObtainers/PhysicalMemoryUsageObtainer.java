package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;

import com.sun.management.OperatingSystemMXBean;

/**
 * 
 * Represents Physical Memory Usage Value obtainer class
 * 
 * @author AlexeyLysenok
 *
 */

public class PhysicalMemoryUsageObtainer extends ParamObtainer{
	
	PhysicalMemoryUsageValue memoryUsage=new PhysicalMemoryUsageValue();
	
	public PhysicalMemoryUsageObtainer() {
	}

	@Override
	public PhysicalMemoryUsageValue getCurrentParamValue() {
		
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();		
		long memory=(long)(osBean.getTotalPhysicalMemorySize()-osBean.getFreePhysicalMemorySize());		
		memoryUsage.setValue(memory);		
		return memoryUsage;
	}

	@Override
	public PhysicalMemoryUsageValue getLastParamValue() {
		if(memoryUsage.getValue()==null){
			memoryUsage=getCurrentParamValue();
		}
		return memoryUsage;
	}

	@Override
	public String getParamName() {
		return memoryUsage.paramInfo.getName();
	}
	
	@Override
	public void print(){
		System.out.println("Using RAM:");
		System.out.println(memoryUsage.getValue().toString()+" bytes");
		
	}

}
