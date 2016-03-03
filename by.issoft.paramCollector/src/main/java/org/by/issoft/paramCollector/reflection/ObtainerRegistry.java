package org.by.issoft.paramCollector.reflection;

import java.util.ArrayList;
import java.util.List;

import org.by.issoft.paramCollector.ParamObtainer;

public class ObtainerRegistry {

	private static List<ParamObtainer<?>> obtainers = new ArrayList<>();

	static {
		String packageName = "org.by.issoft.paramCollector.paramObtainers";
		List<Class<?>> classes = ClassFinder.find(packageName);
		for (Class<?> c : classes) {
			try {
				ParamObtainer<?> obtainer = (ParamObtainer<?>) c.newInstance();
				obtainers.add(obtainer);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<ParamObtainer<?>> getObtainers() {

		return new ArrayList<>(obtainers);

	}

	public static boolean hasObtainer(String paramName) {
		obtainers = getObtainers();
		boolean b = false;
		for (ParamObtainer<?> paramObtainer : obtainers) {
			if (paramObtainer.getParamInfo().getName().equals(paramName)) {
				b = true;
			}
		}
		return b;
	}

	public static ParamObtainer<?> findObtainer(String paramName) {
		obtainers = getObtainers();
		ParamObtainer<?> obtainer = null;
		for (ParamObtainer<?> paramObtainer : obtainers) {
			if (paramObtainer.getParamInfo().getName().equals(paramName)) {
				obtainer = paramObtainer;
			}
		}
		return obtainer;
	}

}
