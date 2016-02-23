package org.by.issoft.paramCollector.params;

import java.util.List;

/**
 * 
 * Represents vector type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class VectorParamValue<T> extends ParamValueAbstract<List<T>> {

	public VectorParamValue(List<T> value) {
		super(value);
	}

	@Override
	public String toString() {

		return getValue().toString();
	}

}
