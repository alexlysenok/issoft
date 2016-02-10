package org.by.issoft.paramCollector.paramObtainers;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;

public class InstalledAppsObtainer extends ParamObtainer{

	private InstalledAppsValue apps=new InstalledAppsValue();
	
	public InstalledAppsObtainer() {
		
	}
	
	@Override
	public InstalledAppsValue getCurrentParamValue() {
		// TODO Auto-generated method stub
		return apps;
	}

	@Override
	public InstalledAppsValue getLastParamValue() {
		// TODO Auto-generated method stub
		return apps;
	}

	@Override
	public String getParamName() {
		// TODO Auto-generated method stub
		return apps.paramInfo.getName();
	}
	
}
