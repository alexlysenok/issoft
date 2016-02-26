package org.by.issoft.paramCollector;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.by.issoft.paramCollector.XXX.ParamCollector;
import org.by.issoft.paramCollector.dataStorage.DataBaseStorage;
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

/**
 * 
 * @author AlexeyLysenok
 *
 **/

public class Main {

	static List<ParamObtainer<?>> obtainers = ObtainerRegistry.getObtainers();
	static DataStorage memoryStorage = new MemoryStorage();
	static DataStorage dbStorage = new DataBaseStorage();

	public static void main(String[] args) {

		// for (ParamObtainer<?> obtainer : obtainers) {
		// memoryStorage.addToStorage(obtainer.getParamInfo(),
		// obtainer.getCurrentParamValue(), new Date());
		// }
		// memoryStorage.printStorage();

		for (ParamObtainer<?> obtainer : obtainers) {
			dbStorage.addToStorage(obtainer.getParamInfo(), obtainer.getCurrentParamValue(), new Date());
		}

	}

}
