package org.module1.parameter.scalar;

import org.module1.parameter.ScalarParam;

public class CurrentTime extends ScalarParam {
	
	public CurrentTime(){
		this.name="TIME";
		this.commands=new String[3];
		this.commands[0]="cd /d D:/Workspace/Training/project/module1/src/main/resources\n";
		this.commands[1]="ECHO "+this.getName()+">>myBatInfo.txt\n";
		this.commands[2]="time /t>>myBatInfo.txt\n";
	}
}
