package org.by.issoft.paramCollector.paramObtainers;

import java.lang.management.ManagementFactory;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.scalarParamValues.FreePhysicalMemoryValue;

import com.sun.management.OperatingSystemMXBean;

/**
 * 
 * Represents Free Physical Memory obtainer class
 * 
 * @author AlexeyLysenok
 *
 */

public class FreePhysicalMemoryObtainer extends ParamObtainer{

	FreePhysicalMemoryValue freeMemory=new FreePhysicalMemoryValue();
	
	public FreePhysicalMemoryObtainer() {
	}
	
	@Override
	public FreePhysicalMemoryValue getCurrentParamValue() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();		
		long memory=osBean.getFreePhysicalMemorySize();		
		freeMemory.setValue(memory);		
		return freeMemory;
	}


	@Override
	public FreePhysicalMemoryValue getLastParamValue() {
		if(freeMemory.getValue()==null){
			freeMemory=getCurrentParamValue();
		}
		return freeMemory;
	}

	@Override
	public String getParamName() {
		return freeMemory.paramInfo.getName();
	}
	
	@Override
	public void print(){
		System.out.println("Free RAM:");
		System.out.println(freeMemory.getValue().toString()+" bytes");
		
	}

}
