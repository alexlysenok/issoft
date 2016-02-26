package org.by.issoft.paramCollector.dao;

import java.util.Date;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;

public interface ParamDAO {

	void save(Param param, ParamValue paramValue, Date date);

	void delete();

	ParamValue find(String name);
}
