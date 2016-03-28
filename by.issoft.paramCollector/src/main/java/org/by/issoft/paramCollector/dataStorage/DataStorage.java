package org.by.issoft.paramCollector.dataStorage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

/**
 * Represents the param value data storage logic .
 */

public interface DataStorage {

	void addToStorage(Param param, ParamValue paramValue, Date date, String host);

	ParamValue getMaxValue(Param param);

	Long getAverageValue(Param param);

	Map<Date, ParamValueAbstract<?>> getAllValues(Param param);

	List<ParamValueAbstract<?>> getTabularChanges(Param param);

	Map<Date, ParamValueAbstract<?>> getAllStorage();

}
