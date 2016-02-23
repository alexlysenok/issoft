package org.by.issoft.paramCollector.params;

import java.util.List;

/**
 * 
 * Represents tabular type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class TabularParamValue<T> extends ParamValueAbstract<List<T>> {

	public TabularParamValue(List<T> value) {
		super(value);
	}

	@Override
	public String toString() {

		return getValue().toString();
	}

}
