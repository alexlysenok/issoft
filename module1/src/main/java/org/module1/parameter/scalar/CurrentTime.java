package org.module1.parameter.scalar;

import org.module1.parameter.ScalarParam;

public class CurrentTime extends ScalarParam {
	
	public CurrentTime(){
		this.name="TIME";
		this.command="time /t>>myBatInfo.txt\n";
		
	}
}
