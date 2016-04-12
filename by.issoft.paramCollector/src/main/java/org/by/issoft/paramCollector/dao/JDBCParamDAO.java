package org.by.issoft.paramCollector.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.reflection.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.google.gson.Gson;

import org.by.issoft.paramCollector.ParamObtainer;

public class JDBCParamDAO extends JdbcDaoSupport implements ParamDAO {

	@Autowired
	private Registry registry;

	private static final String INSERT_QUERY = "INSERT INTO Param(name, host, type, time, value) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_BY_NAME_QUERY = "SELECT value FROM Param WHERE name = ?";
	private static final String SELECT_ALL_QUERY = "SELECT time,value FROM Param WHERE name = ?";
	private static final String SELECT_ALL_STORAGE_QUERY = "SELECT name,time,value FROM Param";
	private static final String DELETE_BY_NAME_QUERY = "DELETE FROM Param WHERE name = ?";

	public JDBCParamDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ParamValueAbstract<?>> findByName(Param param) {

		List<ParamValueAbstract<?>> foundParams = new ArrayList<>();
		Gson gson = new Gson();

		List<String> jsonList = getJdbcTemplate().queryForList(SELECT_BY_NAME_QUERY, String.class, param.getName());

		for (String s : jsonList) {
			Object paramValue = gson.fromJson(s, param.getParamClass());
			foundParams.add((ParamValueAbstract<?>) paramValue);
		}

		return foundParams;

	}

	@Override
	public void save(Param param, ParamValueAbstract<?> paramValue, Date date, String host) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		Gson gson = new Gson();
		Object value1 = paramValue;
		String json = gson.toJson(value1);

		getJdbcTemplate().update(INSERT_QUERY, param.getName(), host, param.getType().name(), currentTime, json);
	}

	@Override
	public void deleteByName(Param param) {
		getJdbcTemplate().update(DELETE_BY_NAME_QUERY, param.getName());

	}

	@Override
	public Map<Date, ParamValueAbstract<?>> findAll(Param param) {

		Gson gson = new Gson();
		Map<Date, ParamValueAbstract<?>> foundParams = new HashMap<>();

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_QUERY, param.getName());
		for (Map<String, Object> row : rows) {
			String value = (String) row.get("value");
			Date date = (Date) row.get("time");
			Object paramValue = gson.fromJson(value, param.getParamClass());

			foundParams.put(date, (ParamValueAbstract<?>) paramValue);
		}

		return foundParams;

	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAll() {

		Param param = null;
		List<ParamObtainer<?>> obtainers = registry.getObtainers();
		Gson gson = new Gson();
		Map<Date, ParamValueAbstract<?>> foundParams = new HashMap<>();

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_STORAGE_QUERY);
		for (Map<String, Object> row : rows) {
			Date date = (Date) row.get("time");
			String name = (String) row.get("name");
			String value = (String) row.get("value");
			for (ParamObtainer<?> paramObtainer : obtainers) {
				if (name.equals(paramObtainer.getParamInfo().getName())) {
					param = paramObtainer.getParamInfo();
				}
			}
			Class<?> class2 = param.getParamClass();
			Object paramValue = gson.fromJson(value, class2);
			foundParams.put(date, (ParamValueAbstract<?>) paramValue);
		}
		return foundParams;

	}

}
