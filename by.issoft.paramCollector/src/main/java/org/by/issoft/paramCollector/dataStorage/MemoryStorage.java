package org.by.issoft.paramCollector.dataStorage;

import static java.util.stream.Collectors.toList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.ScalarParamValue;
import org.by.issoft.paramCollector.params.TabularParamValue;

/**
 * Represents the memory data storage. Keeps the param values in memory
 */

public class MemoryStorage implements DataStorage {

	public static Map<Param, Map<Date, ParamValueAbstract<?>>> paramStorage = new HashMap<>();

	@Override
	public void addToStorage(Param param, ParamValue value, Date date) {

		Map<Date, ParamValueAbstract<?>> paramValues = paramStorage.get(param);
		if (paramValues == null) {
			paramValues = new LinkedHashMap<Date, ParamValueAbstract<?>>();
			paramStorage.put(param, paramValues);
		}
		paramValues.put(date, (ParamValueAbstract<?>) value);

	}

	@Override
	public ParamValueAbstract<?> getMaxValue(Param param) {
		if (param.getType().equals(ParamType.SCALAR)) {
			if (paramStorage.containsKey(param)) {

				List<ParamValueAbstract<?>> list = new ArrayList<>(paramStorage.get(param).values());

				Collections.sort(list);

				return list.get(list.size() - 1);
			} else {
				System.out.println("NO SUCH PARAM INDA STORAGE ");
			}

		} else {
			System.out.println("NON SCALAR PARAM");
		}
		return null;

	}

	@Override
	public Long getAverageValue(Param param) {
		if (param.getType().equals(ParamType.SCALAR)) {

			if (paramStorage.containsKey(param)) {

				List<ScalarParamValue<?>> scalars = paramStorage.get(param).values().stream().map(i -> (ScalarParamValue<?>) i).collect(toList());
				if (scalars.get(0).getValue() instanceof Number) {
					long sum = 0;
					for (ScalarParamValue<?> paramValue : scalars) {
						sum += (long) paramValue.getLongValue();
					}
					Long result = (long) (sum / scalars.size());
					return result;
				} else {
					System.out.println("IMPOSSIBLE TO COUNT AN AVERAGE FOR THIS PARAM TYPE");
				}
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
		Iterator<Entry<Param, Map<Date, ParamValueAbstract<?>>>> iterator = paramStorage.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Param, LinkedHashMap<LocalTime, ParamValueAbstract<?>>> param = (Entry) iterator.next();
			System.out.println(param.getKey() + ":::" + param.getValue());

		}
	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAllValues(Param param) {
		if (paramStorage.containsKey(param)) {
			return new LinkedHashMap<>(paramStorage.get(param));
		} else {
			System.out.println("NO SUCH PARAM INDA STORAGE ");
		}
		return null;
	}

	@Override
	public List<ParamValueAbstract<?>> getTabularChanges(Param param) {
		if (param.getType().equals(ParamType.TABULAR)) {
			if (paramStorage.containsKey(param)) {

				List<ParamValue> list = new ArrayList<>(paramStorage.get(param).values());
				System.out.println(list.size());
				List<TabularParamValue<?>> tabulars = new ArrayList<>();
				for (ParamValue paramValue : list) {
					tabulars.add((TabularParamValue<?>) paramValue);
				}
				Map<Integer, TabularParamValue<?>> unicTabulars = new TreeMap<>();
				for (TabularParamValue<?> paramValue : tabulars) {
					Integer size = new Integer(paramValue.getValue().size());
					if (!unicTabulars.containsKey(size)) {
						unicTabulars.put(size, paramValue);
					}
				}
				ArrayList<ParamValue> params = new ArrayList<>(unicTabulars.values());
				System.out.println(params);

			} else {
				System.out.println("NO SUCH PARAM INDA STORAGE ");
			}

		} else {
			System.out.println("NON TABULAR PARAM");
		}
		return null;
	}

}
