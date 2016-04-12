package org.by.issoft.paramCollector.deprecared;

import org.by.issoft.paramCollector.dao.JDBCParamDAO;
import org.by.issoft.paramCollector.dao.ParamDAO;
import org.springframework.stereotype.Component;

@Component
public class ParamDAOFactory implements DAOFactory {

	@Override
	public ParamDAO getParamDAO() {

		return new JDBCParamDAO();

	}

}
