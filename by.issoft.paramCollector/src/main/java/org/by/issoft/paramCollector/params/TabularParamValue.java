package org.by.issoft.paramCollector.params;

import java.util.List;

/**
 * 
 * Represents tabular type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class TabularParamValue<T> extends ParamValue<List<T>> {

	// implement abstract table logic

	public TabularParamValue() {

	}

	public TabularParamValue(List<T> value) {
		super(value);
	}

}
