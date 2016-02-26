package org.by.issoft.paramCollector;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Date;

import org.by.issoft.paramCollector.dataStorage.MemoryStorage;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.junit.Test;
import org.omg.CORBA.LongHolder;

import com.sun.media.jfxmedia.events.NewFrameEvent;

/**
 * Check if the MemoryStorage methods works correctly.
 */

public class MemoryStorageTest {

	@Test
	public void testMaxValue() {

		MemoryStorage storage = new MemoryStorage();
		PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();

		storage.addToStorage(obtainer.getParamInfo(), new PhysicalMemoryUsageValue(20L), new Date(1220227200));
		storage.addToStorage(obtainer.getParamInfo(), new PhysicalMemoryUsageValue(30L), new Date(1220832000));
		storage.addToStorage(obtainer.getParamInfo(), new PhysicalMemoryUsageValue(10L), new Date(1221436800));

		long max = (long) storage.getMaxValue(obtainer.getParamInfo()).getValue();

		assertEquals("the max value must be 30.0 ", 30L, max);

	}

	@Test
	public void testAverageValue() throws Exception {

		MemoryStorage storage = new MemoryStorage();
		PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();

		storage.addToStorage(obtainer.getParamInfo(), new PhysicalMemoryUsageValue(10L), new Date(1220227200));
		storage.addToStorage(obtainer.getParamInfo(), new PhysicalMemoryUsageValue(30L), new Date(1220832000));
		storage.addToStorage(obtainer.getParamInfo(), new PhysicalMemoryUsageValue(20L), new Date(1221436800));

		long average = storage.getAverageValue(obtainer.getParamInfo());

		assertEquals("the average value must be 20.0 ", 20L, average);

	}

}
