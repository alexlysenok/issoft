package org.by.issoft.paramCollector.dataStorage;

import static java.util.stream.Collectors.toList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.ScalarParamValue;

/**
 * Represents the memory data storage. Keeps the param values in memory
 */

public class MemoryStorage extends AbstractDataStorage {

	public static Map<Param, Map<Date, ParamValueAbstract<?>>> paramStorage = new HashMap<>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	@Override
	public ParamValue getMaxValue(Param param) {
		lock.readLock().lock();
		try {
			return super.getMaxValue(param);
		} finally {
			lock.readLock().unlock();
		}

	}

	@Override
	public Long getAverageValue(Param param) {
		lock.readLock().lock();
		try {
			return super.getAverageValue(param);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<ParamValueAbstract<?>> getTabularChanges(Param param) {
		lock.readLock().lock();
		try {
			return super.getTabularChanges(param);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAllStorage() {
		lock.readLock().lock();
		try {
			@SuppressWarnings("unchecked")
			Map<Date, ParamValueAbstract<?>> list = (Map<Date, ParamValueAbstract<?>>) paramStorage.values();
			return list;

		} finally {
			lock.readLock().unlock();
		}

	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAllValues(Param param) {
		lock.readLock().lock();
		try {
			if (paramStorage.containsKey(param)) {
				return new LinkedHashMap<>(paramStorage.get(param));
			} else {
				System.out.println("NO SUCH PARAM INDA STORAGE ");
			}
		} finally {
			lock.readLock().unlock();
		}

		return null;

	}

	@Override
	public void addToStorage(Param param, ParamValue paramValue, Date date, String host) {

		lock.writeLock().lock();
		try {
			Map<Date, ParamValueAbstract<?>> paramValues = paramStorage.get(param);
			if (paramValues == null) {
				paramValues = new LinkedHashMap<Date, ParamValueAbstract<?>>();
				paramStorage.put(param, paramValues);
			}
			paramValues.put(date, (ParamValueAbstract<?>) paramValue);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public boolean storageContainsValue(Param param) {
		if (paramStorage.containsKey(param)) {
			return true;
		} else
			return false;
	}

	@Override
	public List<ParamValueAbstract<?>> getParamValues(Param param) {

		List<ParamValueAbstract<?>> list = new ArrayList<>(paramStorage.get(param).values());
		return list;
	}

	@Override
	public List<ScalarParamValue<?>> getScalarParamValues(Param param) {
		List<ScalarParamValue<?>> scalars = paramStorage.get(param).values().stream().map(i -> (ScalarParamValue<?>) i).collect(toList());
		return scalars;
	}

	public static void printStorage() {

		Iterator<?> iterator = paramStorage.entrySet().iterator();
		while (iterator.hasNext()) {

			@SuppressWarnings("unchecked")
			Map.Entry<ParamValueAbstract<?>, LinkedHashMap<LocalTime, ParamValueAbstract<?>>> param = (Entry<ParamValueAbstract<?>, LinkedHashMap<LocalTime, ParamValueAbstract<?>>>) iterator.next();
			System.out.println(param.getKey() + ":::" + param.getValue());
		}

	}

}
