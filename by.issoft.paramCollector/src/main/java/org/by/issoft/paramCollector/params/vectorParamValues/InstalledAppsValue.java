package org.by.issoft.paramCollector.params.vectorParamValues;

import java.util.List;

/**
 * 
 * Represents installed apps param value
 * 
 * @author AlexeyLysenok
 *
 */

import org.by.issoft.paramCollector.params.VectorParamValue;

public class InstalledAppsValue extends VectorParamValue<String> {

	public InstalledAppsValue() {

	}

	public InstalledAppsValue(List<String> list) {
		super(list);
	}

}
