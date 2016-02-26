package org.by.issoft.paramCollector.dataStorage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.by.issoft.paramCollector.dao.ParamDAO;
import org.by.issoft.paramCollector.dao.ParamDAOFactory;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

public class DataBaseStorage implements DataStorage {

	ParamDAO dao = new ParamDAOFactory().getParamDAO();

	@Override
	public void addToStorage(Param param, ParamValue paramValue, Date date) {
		dao.save(param, paramValue, date);
	}

	@Override
	public ParamValue getMaxValue(Param param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAverageValue(Param param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAllValues(Param param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParamValueAbstract<?>> getTabularChanges(Param param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printStorage() {
		// TODO Auto-generated method stub

	}

}
