package org.by.issoft.paramCollector.paramObtainers;



import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;


public class DisksInfoObtainer extends ParamObtainer{
	
	private DisksInfoValue disks=new DisksInfoValue();
	

	public DisksInfoObtainer() {
	}
	
	@Override
	public DisksInfoValue getCurrentParamValue() {
		
	
		DisksInfoValue.Disk disk=disks.new Disk();
		
		return disks;
	}

	@Override
	public DisksInfoValue getLastParamValue() {
		return disks;
	}

	@Override
	public String getParamName() {
		return disks.paramInfo.getName();
	}
	
	
	


}
