package org.by.issoft.paramCollector.paramObtainers;

import java.util.Date;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.scalarParamValues.CurrentTimeValue;

/**
 * 
 * Represents current time param value
 * 
 * @author AlexeyLysenok
 *
 */

public class CurrentTimeObtainer extends ParamObtainer {
	
	private CurrentTimeValue time=new CurrentTimeValue();
	
	public CurrentTimeObtainer() {
	}
	
	
	@Override
	public CurrentTimeValue getCurrentParamValue() {	
		time.setValue(new Date());		
		return time;
	}

	@Override
	public CurrentTimeValue getLastParamValue() {

		return time;
	}

	@Override
	public String getParamName() {
		return time.paramInfo.getName();
	}
	
	/*
	
	public class CurrentTimeInner extends ScalarParamValue<Date>{
		Date value;
		
		public CurrentTimeInner() {
			this.paramInfo.setName("CURRENT_TIME");
		}
		
		public Date getValue() {
			return value;
		}

		public void setValue(Date value) {
			this.value = value;
		}
	}
	*/

}
