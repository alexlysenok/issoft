package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.FreePhysicalMemoryValue;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;

import com.sun.pisces.GradientColorMap;

public class DataStorage {

	private static LinkedHashMap<ParamValue<TimeValue>, LocalTime> timeValues = new LinkedHashMap<>();
	private static LinkedHashMap<ParamValue<FreePhysicalMemoryValue>, LocalTime> useMemoryValues = new LinkedHashMap<>();
	private static LinkedHashMap<ParamValue<PhysicalMemoryUsageValue>, LocalTime> freeMemoryValues = new LinkedHashMap<>();
	private static LinkedHashMap<ParamValue<TimeValue>, LocalTime> iAppsValues = new LinkedHashMap<>();
	private static LinkedHashMap<ParamValue<TimeValue>, LocalTime> diskInfoValues = new LinkedHashMap<>();

	public static <T extends ParamValue<T>> LinkedHashMap<ParamValue<T>, LocalTime> getMap(T value) {

		ArrayList<LinkedHashMap<ParamValue<T>, LocalTime>> maps = new ArrayList<>();
		// maps.add(timeValues);

		return null;

	}

}
