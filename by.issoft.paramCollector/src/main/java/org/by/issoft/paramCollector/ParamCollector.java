package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;

public class ParamCollector extends TimerTask {

	static LinkedHashMap<LocalTime, TimeValue> timeValues = new LinkedHashMap<>();
	// static LinkedHashMap<LocalTime, ParamWrapper> timeValues = new
	// LinkedHashMap<>();
	private CurrentTimeObtainer obtainer = new CurrentTimeObtainer();

	@Override
	public void run() {

		LocalTime now = LocalTime.now();

		// ParamWrapper paramWrapper = new ParamWrapper();
		// paramWrapper.setParamValue(obtainer.getCurrentParamValue());
		// timeValues.put(now, paramWrapper);

	}

	public static void executeCollecting() {
		Timer timer = new Timer();
		Timer timer2 = new Timer();

		timer.scheduleAtFixedRate(new ParamCollector(), 0, 5000);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				timer.cancel();
				timer.purge();
				printResult();

			}
		}, 10000);

		timer2.cancel();
		timer2.purge();

	}

	public static void printResult() {
		System.out.println("Collecting ready");
		System.out.println(timeValues);
	}

	public static void main(String[] args) {

		// timer.scheduleAtFixedRate(new ParamCollector(), 0, 5000);
		//
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// timer.cancel();

		// timer.purge();

		executeCollecting();

	}

}
