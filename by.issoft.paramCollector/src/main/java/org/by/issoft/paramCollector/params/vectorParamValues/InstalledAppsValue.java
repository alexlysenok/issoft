package org.by.issoft.paramCollector.params.vectorParamValues;

/**
 * 
 * Represents installed apps param value
 * 
 * @author AlexeyLysenok
 *
 */

import java.util.List;

import org.by.issoft.paramCollector.params.VectorParamValue;

public class InstalledAppsValue extends VectorParamValue<List<String>>{
	
	List<String> apps;
	
	public InstalledAppsValue() {
		this.paramInfo.setName("INSTALLED_APPS");
	}

	public List<String> getValue() {
		return apps;
	}

	public void setValue(List<String> apps) {
		this.apps = apps;
	}

}
