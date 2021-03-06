package org.by.issoft.paramCollector.params.tabularParamValues;

/**
 * 
 * Represents disks info param value
 * 
 * @author AlexeyLysenok
 *
 */

import java.util.List;

import org.by.issoft.paramCollector.params.TabularParamValue;

public class DisksInfoValue extends TabularParamValue<List<DisksInfoValue.Disk>> {

	List<Disk> disks;

	public DisksInfoValue() {
		this.paramInfo.setName("DISKS_INFO");
	}

	public List<Disk> getValue() {
		return disks;
	}

	public void setValue(List<Disk> disks) {
		this.disks = disks;
	}

	//use static  nested class
	public class Disk {
		String id;
		Long freeSpace;
		String name;

		public Disk() {
		}

		public Disk(String id, Long freeSpace, String name) {
			this.id = id;
			this.freeSpace = freeSpace;
			this.name = name;
		}

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

		@Override
		public String toString() {
			String string = "Disk:" + id + " | FreeSpace:" + freeSpace + " bytes | VolumeName:" + name;
			return string;
		}
	}
}
