package org.by.issoft.paramCollector.dataStorage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.ScalarParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.by.issoft.paramCollector.params.scalarParamValues.TimeValue;

public class MemoryStorage implements DataStorage {

	public static HashMap<Class<?>, LinkedHashMap<LocalTime, ParamValueAbstract>> paramStorage = new HashMap<>();

	@Override
	public void addToStorage(ParamValueAbstract value, LocalTime time) {

		if (paramStorage.containsKey(value.getClass())) {
			LinkedHashMap<LocalTime, ParamValueAbstract> temp = new LinkedHashMap<>(paramStorage.get(value.getClass()));
			temp.put(time, value);
			paramStorage.get(value.getClass()).putAll(temp);
		} else {
			paramStorage.put(value.getClass(), new LinkedHashMap<LocalTime, ParamValueAbstract>());
			paramStorage.get(value.getClass()).put(time, value);
		}

		// params.put(value.getClass(), value);

	}

	@Override
	public Long getMaxValue(ParamValue value) {
		if (value instanceof ScalarParamValue) {
			if (paramStorage.containsKey(value.getClass())) {

				List<ParamValue> list = new ArrayList<>(paramStorage.get(value.getClass()).values());

				Collections.sort(list);

				ScalarParamValue<?> paramValue = (ScalarParamValue<?>) list.get(list.size() - 1);

				return paramValue.getDoubleValue();
			} else {
				System.out.println("NO SUCH PARAM INDA STORAGE ");
			}

		} else {
			System.out.println("NON SCALAR PARAM");
		}
		return null;

	}

	@Override
	public Long getAverageValue(ParamValue value) {
		if (value instanceof ScalarParamValue) {
			if (paramStorage.containsKey(value.getClass())) {
				List<ParamValue> list = new ArrayList<>(paramStorage.get(value.getClass()).values());
				List<ScalarParamValue<?>> scalars = new ArrayList<>();
				for (ParamValue paramValue : list) {
					scalars.add((ScalarParamValue<?>) paramValue);
				}
				Long sum = null;

				for (ScalarParamValue<?> paramValue : scalars) {
					sum += paramValue.getDoubleValue();
				}
				Long result = sum / scalars.size();

			} else {
				System.out.println("NO SUCH PARAM INDA STORAGE ");
			}

		} else {
			System.out.println("NON SCALAR PARAM");
		}
		return null;

	}

	@Override
	public void printStorage() {
		Iterator<Entry<Class<?>, LinkedHashMap<LocalTime, ParamValueAbstract>>> iterator = paramStorage.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Class<?>, LinkedHashMap<LocalTime, ParamValueAbstract>> param = (Entry) iterator.next();
			System.out.println(param.getKey().getSimpleName() + ":::" + param.getValue());

		}
	}

	@Override
	public List<ParamValueAbstract> getAllValues(ParamValue value) {
		if (paramStorage.containsKey(value.getClass())) {

			ArrayList<ParamValueAbstract> list = new ArrayList<>(paramStorage.get(value.getClass()).values());
			System.out.println(list);
			return list;

		} else {
			System.out.println("NO SUCH PARAM INDA STORAGE ");
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
