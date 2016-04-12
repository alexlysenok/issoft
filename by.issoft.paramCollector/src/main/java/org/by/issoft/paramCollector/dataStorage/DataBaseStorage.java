package org.by.issoft.paramCollector.dataStorage;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.by.issoft.paramCollector.dao.ParamDAO;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.ScalarParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dataBaseStorage")
public class DataBaseStorage extends AbstractDataStorage {

	// ParamDAO dao = new ParamDAOFactory().getParamDAO();

	@Autowired
	ParamDAO dao;

	@Override
	public void addToStorage(Param param, ParamValue paramValue, Date date, String host) {

		dao.save(param, (ParamValueAbstract<?>) paramValue, date, host);
	}

	@Override
	public boolean storageContainsValue(Param param) {
		return true;
	}

	@Override
	public List<ParamValueAbstract<?>> getParamValues(Param param) {
		List<ParamValueAbstract<?>> list = dao.findByName(param);
		if (list.size() == 0) {
			System.out.println("NO SUCH PARAM INDA STORAGE ");
		}
		return list;
	}

	@Override
	public List<ScalarParamValue<?>> getScalarParamValues(Param param) {
		List<ScalarParamValue<?>> scalars = getParamValues(param).stream().map(i -> (ScalarParamValue<?>) i).collect(toList());
		if (scalars.size() == 0) {
			System.out.println("NO SUCH PARAM INDA STORAGE ");
		}
		return scalars;
	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAllValues(Param param) {
		Map<Date, ParamValueAbstract<?>> list = dao.findAll(param);
		if (list.size() == 0) {
			System.out.println("NO ANY PARAM INDA STORAGE ");
		}
		return list;
	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAllStorage() {
		Map<Date, ParamValueAbstract<?>> list = dao.getAll();
		if (list.size() == 0) {
			System.out.println("NO ANY PARAM INDA STORAGE ");
		}
		return list;
	}

}
