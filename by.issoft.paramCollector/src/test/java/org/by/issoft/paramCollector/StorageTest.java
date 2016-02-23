package org.by.issoft.paramCollector;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;

import org.by.issoft.paramCollector.dataStorage.MemoryStorage;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.junit.Test;

public class StorageTest {

	@Test
	public void testMaxValue() {

		String s = "";
		try {
			MemoryStorage storage = new MemoryStorage();
			storage.addToStorage(new PhysicalMemoryUsageValue(10L), LocalTime.now());
			Thread.sleep(500);
			storage.addToStorage(new PhysicalMemoryUsageValue(30L), LocalTime.now());
			Thread.sleep(500);
			storage.addToStorage(new PhysicalMemoryUsageValue(20L), LocalTime.now());

		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		// assertEquals("the max value must be 30 ", "30", string);

	}

}
