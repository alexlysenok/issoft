package org.by.issoft.paramCollector.dao;

public class ParamDAOFactory implements DAOFactory {

	@Override
	public ParamDAO getParamDAO() {

		return new JDBCParamDAO();
	}

}
