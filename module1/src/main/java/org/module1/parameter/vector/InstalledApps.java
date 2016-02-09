package org.module1.parameter.vector;

import java.util.List;

import org.module1.parameter.VectorParam;
import org.module1.parameter.tabular.DiskInfo.Disk;

public class InstalledApps extends VectorParam<List<String>>
{

	List<String> value;

	@Override
	public List<String> getValue() {
		return value;
	}
	
	@Override
	public void setValue(List<String> value) {
		this.value = value;
	}

	
	public InstalledApps(){
		this.name="INSTALLED APPS";
		this.commands=new String[1];
		//this.commands[0]="ECHO "+this.getName()+">>myBatInfo.txt\n";
		this.commands[0]="wmic /OUTPUT:D:\\Workspace\\Training\\project\\module1\\src\\main\\resources\\myBatInfo.txt product get name\n";
		
		
		
	}
}
