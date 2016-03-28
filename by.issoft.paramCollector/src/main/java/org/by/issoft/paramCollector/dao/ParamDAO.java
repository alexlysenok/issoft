package org.by.issoft.paramCollector.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

public interface ParamDAO {

	void save(Param param, ParamValueAbstract<?> paramValue, Date date, String host);

	void deleteByName(Param param);

	List<ParamValueAbstract<?>> findByName(Param param);

	Map<Date, ParamValueAbstract<?>> findAll(Param param);

	Map<Date, ParamValueAbstract<?>> getAll();

}
