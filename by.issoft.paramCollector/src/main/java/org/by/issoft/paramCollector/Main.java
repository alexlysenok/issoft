package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;

import javafx.animation.Timeline;

public class Main {

	public static void main(String[] args) {

		List<ParamObtainer> obtainers = ObtainerRegistry.registerObtainers();
		for (ParamObtainer obtainer : obtainers) {
			System.out.println(obtainer.getParamName());
			System.out.println(obtainer.getCurrentParamValue());
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}

		// CurrentTimeObtainer obtainer = new CurrentTimeObtainer();
		// LocalTime time = obtainer.getCurrentParamValue().getValue();
		// System.out.println(time);
		//
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// time = obtainer.getCurrentParamValue().getValue();
		// System.out.println(time);
		//
		// time = obtainer.getLastParamValue().getValue();
		// System.out.println(time);

	}

}
