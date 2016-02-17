package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.paramObtainers.FreePhysicalMemoryObtainer;
import org.by.issoft.paramCollector.paramObtainers.InstalledAppsObtainer;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.FreePhysicalMemoryValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.by.issoft.paramCollector.params.vectorParamValues.InstalledAppsValue;

public class Main {

	public static void main(String[] args) {

		// List<ParamObtainer> obtainers = ObtainerRegistry.getObtainers();
		// for (ParamObtainer obtainer : obtainers) {
		// System.out.println(obtainer.getParamName());
		// System.out.println(obtainer.getCurrentParamValue());
		// System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		//
		// }

		// CurrentTimeObtainer obtainer = new CurrentTimeObtainer();
		// LocalTime time = (LocalTime)
		// obtainer.getCurrentParamValue().getValue();
		// System.out.println(time);

		// DisksInfoObtainer obtainer = new DisksInfoObtainer();
		// DisksInfoValue disksInfoValue = obtainer.getCurrentParamValue();
		// System.out.println(disksInfoValue);

		// FreePhysicalMemoryObtainer obtainer = new
		// FreePhysicalMemoryObtainer();
		// FreePhysicalMemoryValue freePhysicalMemoryValue =
		// (FreePhysicalMemoryValue) obtainer.getCurrentParamValue();
		// System.out.println(freePhysicalMemoryValue);

		// DisksInfoObtainer obtainer = new DisksInfoObtainer();
		// DisksInfoValue disksInfoValue = (DisksInfoValue)
		// obtainer.getCurrentParamValue();
		// System.out.println(disksInfoValue);

		InstalledAppsObtainer obtainer = new InstalledAppsObtainer();
		InstalledAppsValue installedAppsValue = (InstalledAppsValue) obtainer.getCurrentParamValue();
		System.out.println(installedAppsValue);

	}

}
