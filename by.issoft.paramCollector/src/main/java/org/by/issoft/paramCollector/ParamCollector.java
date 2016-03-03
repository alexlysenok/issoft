package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.by.issoft.paramCollector.dataStorage.DataBaseStorage;
import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.paramObtainers.InstalledAppsObtainer;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;

public class ParamCollector implements Runnable {

	private final long duration;
	private final long frequency;
	private final ParamObtainer<?> obtainer;
	private DataStorage storage;
	private Boolean collectingReady;

	private Thread thread;

	public static boolean paused = false;

	public static Object LOCK = new Object();

	private PauseService obj;

	public ParamCollector(DataStorage storage, ParamObtainer<?> obtainer, Long duration, Long frequency, PauseService obj) {
		this.duration = duration;
		this.frequency = frequency;
		this.obtainer = obtainer;
		this.storage = storage;
		thread = new Thread(this);
		thread.start();
		this.obj = obj;
	}

	@Override
	public void run() {

		System.out.println("collecting " + obtainer.getParamInfo().getName() + "...");
		long start = System.currentTimeMillis();
		long end = start + duration;

		while (System.currentTimeMillis() <= end) {
			synchronized (obj) {
				if (paused) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {

					collect();
				}

			}

		}
		System.out.println("finished collecting " + obtainer.getParamInfo().getName());

	}

	private void collect() {

		// storage.addToStorage(obtainer.getParamInfo(),
		// obtainer.getCurrentParamValue(), new Date());
		System.out.println(obtainer.getCurrentParamValue());
		try {
			Thread.sleep(frequency);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();
		PauseService obj = new PauseService();
		ParamCollector collector = new ParamCollector(new DataBaseStorage(), obtainer, 10000L, 1000L, obj);
		obj.start();

	}
}
