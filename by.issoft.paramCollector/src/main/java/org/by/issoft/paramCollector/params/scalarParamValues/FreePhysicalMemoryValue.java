package org.by.issoft.paramCollector.params.scalarParamValues;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ScalarParamValue;

import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.media.jfxmedia.events.NewFrameEvent;

/**
 * 
 * Represents Free Physical Memory param value
 * 
 * @author AlexeyLysenok
 *
 */

public class FreePhysicalMemoryValue extends ScalarParamValue<Long> {

	public FreePhysicalMemoryValue(Long value) {
		super(value);
	}

	@Override
	public int compareTo(ParamValue o) {
		if (o instanceof FreePhysicalMemoryValue) {
			FreePhysicalMemoryValue object = (FreePhysicalMemoryValue) o;
			return getValue().compareTo(object.getValue());
		} else {
			throw new ClassCastException();
		}
	}

	@Override
	public Long getLongValue() {
		Long double1 = new Long(getValue());
		return double1;
	}

}
