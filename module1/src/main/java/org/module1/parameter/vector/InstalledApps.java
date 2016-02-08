package org.module1.parameter.vector;

import org.module1.parameter.VectorParam;

public class InstalledApps extends VectorParam {

	public InstalledApps(){
		this.name="INSTALLED APPS";
		this.commands=new String[1];
		//this.commands[0]="ECHO "+this.getName()+">>myBatInfo.txt\n";
		this.commands[0]="wmic /OUTPUT:D:\\Workspace\\Training\\project\\module1\\src\\main\\resources\\myBatInfo.txt product get name\n";
		
		
		
	}
}
