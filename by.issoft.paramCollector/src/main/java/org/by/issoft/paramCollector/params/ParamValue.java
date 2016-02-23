package org.by.issoft.paramCollector.params;

import org.by.issoft.paramCollector.params.scalarParamValues.Summarable;

public interface ParamValue extends Comparable<ParamValue> {

	Object getValue();

}