package org.by.issoft.paramCollector.params.vectorParamValues;

import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.params.VectorParamValue;

public class InstalledAppsValue extends VectorParamValue<List<String>>{
	
	List<String> apps=new ArrayList<String>();
	
	public InstalledAppsValue() {
		this.paramInfo.setName("INSTALLED_APPS");
	}

	public List<String> getApps() {
		return apps;
	}

	public void setApps(List<String> apps) {
		this.apps = apps;
	}

}
