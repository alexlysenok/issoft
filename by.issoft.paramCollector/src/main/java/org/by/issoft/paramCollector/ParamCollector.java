package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;

import com.sun.glass.ui.TouchInputSupport;

public class ParamCollector extends TimerTask {

	static LinkedHashMap<ParamValue<?>, LocalTime> paramValues = new LinkedHashMap<>();

	private static ParamObtainer obtainer;
	public static Boolean collectingReady;

	@Override
	public void run() {
		collect();

	}

	private void collect() {

		System.out.println();
		LocalTime now = LocalTime.now();
		// paramValues.put(obtainer.getCurrentParamValue(), now);
	}

	public void printMaxValue() {
		ArrayList<ParamValue<?>> list = new ArrayList<>(paramValues.keySet());
		list.get(0);

	}

	public static void executeCollecting(ParamObtainer myObtainer, Long duration, Long frequency) {

		obtainer = myObtainer;

		Timer timer = new Timer();
		Timer timer2 = new Timer();

		timer.scheduleAtFixedRate(new ParamCollector(), 0, frequency);
		System.out.println("Collecting in progress...");
		collectingReady = false;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				timer.cancel();
				timer.purge();
				collectingReady = true;

			}
		}, duration);

		timer2.cancel();
		timer2.purge();

		do {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (collectingReady == false);
		System.out.println("Collecting is ready");
		printAllValues();

	}

	public static void printAllValues() {

		System.out.println("all params: " + paramValues);
	}

	public static void printResult() {

		System.out.println(paramValues);
	}

	public static void main(String[] args) {
		// Long duration = (long) (60 * 1000);
		// Long frequancy = (long) (20 * 1000);
		// CurrentTimeObtainer obtainer1 = new CurrentTimeObtainer();
		// DisksInfoObtainer obtainer1 = new DisksInfoObtainer();
		// executeCollecting(obtainer1, duration, frequancy);

		CurrentTimeObtainer obtainer1 = new CurrentTimeObtainer();
		obtainer1.getCurrentParamValue();
		System.out.println(obtainer1.getGenericType());

		DataStorage.getMap(obtainer1.getGenericType());

	}

}
