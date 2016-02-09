package org.module1.parameter.tabular;

import java.util.ArrayList;
import java.util.List;

import org.module1.parameter.TabularParam;

public class DiskInfo extends TabularParam<DiskInfo.Disk> {

	List<Disk> value;

	@Override
	public List<Disk> getValue() {
		return value;
	}
	
	@Override
	public void setValue(List<Disk> value) {
		this.value = value;
	}

	public DiskInfo() {
		this.name = "DISK INFO";
		this.commands = new String[1];
		this.commands[0] = "wmic /OUTPUT:D:\\Workspace\\Training\\project\\module1\\src\\main\\resources\\myBatInfo.txt logicaldisk get deviceid, volumename, freespace\n";
	}

	public class Disk {
		String id;
		String space;
		String name;
		
		
        /*
		public Disk(String id, String space, String name) {
			this.id = id;
			this.space = space;
			this.name = name;
		}
		*/
		
		public Disk(ArrayList<String> list) {
			this.id = list.get(0);
			this.space = list.get(1);
			this.name = list.get(2);
		}
	}
}
