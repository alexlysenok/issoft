package org.by.issoft.paramCollector.params.tabularParamValues;

import java.util.List;

import org.by.issoft.paramCollector.params.TabularParamValue;

public class DisksInfoValue extends TabularParamValue<List<DisksInfoValue.Disk>>{
	
	List<Disk> disks;
	
	public DisksInfoValue() {
		this.paramInfo.setName("DISKS_INFO");
	}
	
	

	public List<Disk> getDisks() {
		return disks;
	}

	public void setDisks(List<Disk> disks) {
		this.disks = disks;
	}
	
	
	public class Disk {
		String id;
		Long freeSpace;
		String name;
		
		public Disk(){}
		
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		public Long getFreeSpace() {
			return freeSpace;
		}

		public void setFreeSpace(Long freeSpace) {
			this.freeSpace = freeSpace;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
