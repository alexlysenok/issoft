package org.by.issoft.paramCollector.params.vectorParamValues;

import java.util.List;

import org.by.issoft.paramCollector.params.ParamValue;

/**
 * 
 * Represents installed apps param value
 * 
 * @author AlexeyLysenok
 *
 */

import org.by.issoft.paramCollector.params.VectorParamValue;

public class InstalledAppsValue extends VectorParamValue<String> {

	public InstalledAppsValue(List<String> list) {
		super(list);
	}

	@Override
	public int compareTo(ParamValue o) {
		// TODO Auto-generated method stub
		return 0;
	}

	// @Override
	// public ParamValue summarize(ParamValue value) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
