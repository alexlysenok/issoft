package org.module1.parameter.tabular;

import org.module1.parameter.TabularParam;

public class DiskInfo extends TabularParam {
	public DiskInfo() {
		this.name="DISK INFO";
		this.commands=new String[1];
		this.commands[0]="wmic /OUTPUT:D:\\Workspace\\Training\\project\\module1\\src\\main\\resources\\myBatInfo.txt logicaldisk get deviceid, volumename, freespace\n";
	}
}
