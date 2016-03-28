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

	/**
	 * 
	 */
	private static final long serialVersionUID = 3840714936631392879L;

	public TabularParamValue(List<T> value) {
		super(value);
	}

	@Override
	public String toString() {

		return getValue().toString();
	}

}
