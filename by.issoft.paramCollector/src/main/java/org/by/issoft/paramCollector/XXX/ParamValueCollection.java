package org.by.issoft.paramCollector.XXX;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

public class ParamValueCollection {

	private Class<?> paramClass;
	private LocalTime startOfCollecting;
	private LocalTime endOfCollecting;
	private HashMap<ParamValueAbstract<?>, LocalTime> collection;

	public ParamValueCollection(Class<?> paramClass, LocalTime startOfCollecting, LocalTime endOfCollecting, HashMap<ParamValueAbstract<?>, LocalTime> collection) {
		this.paramClass = paramClass;
		this.startOfCollecting = startOfCollecting;
		this.endOfCollecting = endOfCollecting;
		this.collection = collection;
		// TODO Auto-generated constructor stub
	}

	public ParamValue getMaxValue() {

		ArrayList<ParamValueAbstract<?>> list = new ArrayList<>(collection.keySet());

		// list.sort(c);

		return null;

	}

	public Class<?> getParamClass() {
		return paramClass;
	}

	public void setParamClass(Class<?> paramClass) {
		this.paramClass = paramClass;
	}

	public LocalTime getStartOfCollecting() {
		return startOfCollecting;
	}

	public void setStartOfCollecting(LocalTime startOfCollecting) {
		this.startOfCollecting = startOfCollecting;
	}

	public LocalTime getEndOfCollecting() {
		return endOfCollecting;
	}

	public void setEndOfCollecting(LocalTime endOfCollecting) {
		this.endOfCollecting = endOfCollecting;
	}

	public HashMap<ParamValueAbstract<?>, LocalTime> getCollection() {
		return collection;
	}

	public void setCollection(HashMap<ParamValueAbstract<?>, LocalTime> collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {

		String string = "Param: " + paramClass.getSimpleName() + " || collected from: " + startOfCollecting + " to " + endOfCollecting + " ||s values: " + collection;
		return string;
	}

}
