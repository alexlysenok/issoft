package org.by.issoft.paramCollector;

import java.util.ArrayList;
import java.util.List;

public class ObtainerRegistry<T> {

	T value;

	private static ArrayList<ParamObtainer> obtainers = new ArrayList<>();

	static {
		String packageName = "org.by.issoft.paramCollector.paramObtainers";
		List<Class<?>> classes = ClassFinder.find(packageName);
		for (Class<?> c : classes) {
			try {
				ParamObtainer obtainer = (ParamObtainer) c.newInstance();
				obtainers.add(obtainer);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<ParamObtainer> getObtainers() {

		return new ArrayList<>(obtainers);

	}
}
