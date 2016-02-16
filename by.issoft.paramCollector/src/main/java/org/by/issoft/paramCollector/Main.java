package org.by.issoft.paramCollector;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.paramObtainers.CurrentTimeObtainer;
import org.by.issoft.paramCollector.params.ParamValue;

import javafx.animation.Timeline;

public class Main {

	public static void main(String[] args) {

		List<ParamObtainer> obtainers = ObtainerRegistry.getObtainers();
		for (ParamObtainer obtainer : obtainers) {
			System.out.println(obtainer.getParamName());
			System.out.println(obtainer.getCurrentParamValue());
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}

		// CurrentTimeObtainer obtainer = new CurrentTimeObtainer();
		// LocalTime time = obtainer.getCurrentParamValue().getValue();
		// System.out.println(time);
		//
		// time = obtainer.getCurrentParamValue().getValue();
		// System.out.println(time);
		//
		// time = obtainer.getLastParamValue().getValue();
		// System.out.println(time);

	}

}
