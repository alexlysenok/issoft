package org.by.issoft.paramCollector.params;

import java.util.List;

/**
 * 
 * Represents vector type of param
 * 
 * @author AlexeyLysenok
 *
 */

public abstract class VectorParamValue<T> extends ParamValue<List<T>> {

	public VectorParamValue() {

	}

	public VectorParamValue(List<T> value) {
		super(value);
	}

}
