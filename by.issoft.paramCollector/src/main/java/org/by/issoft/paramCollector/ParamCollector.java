package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.dataStorage.DataStorage;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.xml.ParamToCollect;

public abstract class ParamCollector implements Runnable {

	private ParamToCollect collectingParam;
	private DataStorage storage;
	private ParamValueAbstract<?> lastValue;
	private String status = "running";

	private volatile boolean paused = false;
	private volatile boolean stopped = false;

	public ParamCollector(DataStorage storage, ParamToCollect collectingParam) {
		this.storage = storage;
		this.collectingParam = collectingParam;

	}

	@Override
	public void run() {
		System.out.println("collecting " + getCollectingParam().getParamName() + " from " + getCollectingParam().getHost() + "...");

		while (!isStopped()) {
			synchronized (this) {
				while (isPaused()) {
					try {
						wait();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				collect();

				try {
					Thread.sleep(getCollectingParam().getFrequency());
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}

		}

		System.out.println("finished collecting " + getCollectingParam().getParamName() + " from " + getCollectingParam().getHost());
	}

	public abstract ParamValueAbstract<?> collect();

	public void pauseCollecting() {
		paused = true;
		status = "paused";
		System.out.println("------------PAUSED-----------");
	}

	public void resumeCollecting() {
		synchronized (this) {

			paused = false;
			status = "running";
			System.out.println("------------RESUMED-----------");
			// long temp = timePoint;
			// timePoint = System.currentTimeMillis() - temp;
			notify();
		}
	}

	public void stopCollecting() {
		stopped = true;
	}

	public ParamToCollect getCollectingParam() {
		return collectingParam;
	}

	public DataStorage getStorage() {
		return storage;
	}

	public boolean isPaused() {
		return paused;
	}

	public boolean isStopped() {
		return stopped;
	}

	public ParamValueAbstract<?> getLastValue() {
		return lastValue;
	}

	public void setLastValue(ParamValueAbstract<?> lastValue) {
		this.lastValue = lastValue;
	}

	public String getStatus() {
		return status;
	}

}
