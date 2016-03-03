package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class PauseService extends Thread {

	@Override
	public void run() {

		try {
			sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pauseCollecting();

		// Scanner keyboard = new Scanner(System.in);
		// System.out.println("enter anything");
		// if (keyboard.hasNext()) {
		// pauseCollecting();
		// }

	}

	public void pauseCollecting() {
		System.out.println("collecting paused");
		synchronized (this) {
			ParamCollector.paused = true;
			notifyAll();
		}
	}

	public void resumeCollecting() {
		System.out.println("collecting resumed");
		synchronized (this) {
			ParamCollector.paused = false;
			notifyAll();
		}
	}

}
