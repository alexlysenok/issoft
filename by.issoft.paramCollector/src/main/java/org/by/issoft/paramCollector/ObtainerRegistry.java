package org.by.issoft.paramCollector;

import java.util.ArrayList;
import java.util.List;

public class ObtainerRegistry {

	private static ArrayList<ParamObtainer> obtainers = new ArrayList<>();

	public static ArrayList<ParamObtainer> registerObtainers() {
		String packageName = "org.by.issoft.paramCollector.paramObtainers";
		List<Class<?>> classes = ClassFinder.find(packageName);
		for (Class<?> c : classes) {
			try {
				ParamObtainer obtainer = (ParamObtainer) c.newInstance();
				obtainers.add(obtainer);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return obtainers;

	}
}
