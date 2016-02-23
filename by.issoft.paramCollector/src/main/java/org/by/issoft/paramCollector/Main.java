package org.by.issoft.paramCollector;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.by.issoft.paramCollector.XXX.ParamCollector;
import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.dataStorage.MemoryStorage;
import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.paramObtainers.FreePhysicalMemoryObtainer;
import org.by.issoft.paramCollector.paramObtainers.InstalledAppsObtainer;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.scalarParamValues.FreePhysicalMemoryValue;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;
import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.time.temporal.ChronoUnit.NANOS;;

public class Main {

	static List<ParamObtainer<?>> obtainers = ObtainerRegistry.getObtainers();
	static DataStorage storage = new MemoryStorage();

	public static void main(String[] args) {

		// Long duration = (long) (10 * 1000);
		// Long frequancy = (long) (5 * 1000);

		// for (ParamObtainer<?> obtainer : obtainers) {
		// storage.addToStorage(obtainer.getCurrentParamValue(),
		// LocalTime.now());
		// }

		// PhysicalMemoryUsageObtainer obtainer = new
		// PhysicalMemoryUsageObtainer();
		// storage.addToStorage(obtainer.getCurrentParamValue(),
		// LocalTime.now());
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// storage.addToStorage(obtainer.getCurrentParamValue(),
		// LocalTime.now());
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// storage.addToStorage(obtainer.getCurrentParamValue(),
		// LocalTime.now());
		//
		// storage.printStorage();
		//
		// Long l = storage.getMaxValue(new PhysicalMemoryUsageValue(1L));
		// System.out.println(l);

		CurrentTimeObtainer obtainer = new CurrentTimeObtainer();
		storage.addToStorage(obtainer.getCurrentParamValue(), LocalTime.now());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		storage.addToStorage(obtainer.getCurrentParamValue(), LocalTime.now());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		storage.addToStorage(obtainer.getCurrentParamValue(), LocalTime.now());

		storage.printStorage();

		Long l = storage.getMaxValue(new TimeValue(Instant.now()));
		System.out.println(l);

	}

}
