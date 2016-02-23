package org.by.issoft.paramCollector.params.tabularParamValues;

/**
 * 
 * Represents disks info param value
 * 
 * @author AlexeyLysenok
 *
 */

import java.util.List;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.TabularParamValue;

public class DisksInfoValue extends TabularParamValue<DisksInfoValue.Disk> {

	public DisksInfoValue(List<Disk> disks) {
		super(disks);
	}

	public static class Disk {
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
			String string = "Disk:" + id + " - FreeSpace:" + freeSpace + " bytes - VolumeName:" + name;
			return string;
		}
	}

	@Override
	public int compareTo(ParamValue o) {
		// TODO Auto-generated method stub
		return 0;
	}

	// @Override
	// public ParamValue summarize(ParamValue value) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
