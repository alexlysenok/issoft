package org.by.issoft.paramCollector.dataStorage;

import java.time.LocalTime;
import java.util.List;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

public interface DataStorage {

	void addToStorage(ParamValueAbstract value, LocalTime time);

	Long getMaxValue(ParamValue value);

	Long getAverageValue(ParamValue value);

	List<ParamValueAbstract> getAllValues(ParamValue value);

	void printStorage();

}
