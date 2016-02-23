package org.by.issoft.paramCollector.XXX;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

public class ParamCollector {

	private static HashMap<ParamValueAbstract<?>, LocalTime> paramValues = new HashMap<>();
	private static ParamObtainer<?> obtainer;
	private static Boolean collectingReady;
	private LocalTime start;
	private LocalTime end;

	private static void collect() {

		LocalTime now = LocalTime.now();
		System.out.println(obtainer.getCurrentParamValue());
		paramValues.put((ParamValueAbstract<?>) obtainer.getCurrentParamValue(), now);
	}

	public void executeCollecting(ParamObtainer<?> myObtainer, Long duration, Long frequency) {

		obtainer = myObtainer;

		Timer timer = new Timer();
		Timer timer2 = new Timer();

		// timer.scheduleAtFixedRate(new ParamCollector(), 0, frequency);
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				collect();

			}
		}, 0, frequency);

		start = LocalTime.now();
		System.out.println("Collecting in progress...");
		collectingReady = false;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				timer.cancel();
				timer.purge();
				collectingReady = true;
				end = LocalTime.now();

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
		// printAllValues();

		ParamValueCollection collection = new ParamValueCollection(obtainer.getClass(), start, end, paramValues);
		// new DataStorage().putCollection(collection);

	}

	public void printAllValues() {

		System.out.println("all params: " + paramValues);
	}

	public void printResult() {

		System.out.println(paramValues);
	}

}
