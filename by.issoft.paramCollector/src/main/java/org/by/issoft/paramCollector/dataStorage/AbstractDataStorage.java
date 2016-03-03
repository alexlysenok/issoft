package org.by.issoft.paramCollector.dataStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamType;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.ScalarParamValue;
import org.by.issoft.paramCollector.params.TabularParamValue;

public abstract class AbstractDataStorage implements DataStorage {

	public abstract boolean storageContainsValue(Param param);

	public abstract List<ParamValueAbstract<?>> getParamValues(Param param);

	public abstract List<ScalarParamValue<?>> getScalarParamValues(Param param);

	@Override
	public ParamValue getMaxValue(Param param) {
		if (param.getType().equals(ParamType.SCALAR)) {
			if (storageContainsValue(param)) {
				List<ParamValueAbstract<?>> list = getParamValues(param);
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

			if (storageContainsValue(param)) {

				List<ScalarParamValue<?>> scalars = getScalarParamValues(param);
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
	public abstract Map<Date, ParamValueAbstract<?>> getAllValues(Param param);

	@Override
	public List<ParamValueAbstract<?>> getTabularChanges(Param param) {
		if (param.getType().equals(ParamType.TABULAR)) {
			if (storageContainsValue(param)) {

				List<ParamValueAbstract<?>> list = getParamValues(param);
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
