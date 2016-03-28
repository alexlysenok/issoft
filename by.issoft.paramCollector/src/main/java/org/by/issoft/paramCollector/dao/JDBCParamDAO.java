package org.by.issoft.paramCollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.by.issoft.paramCollector.ParamObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import com.google.gson.Gson;

public class JDBCParamDAO implements ParamDAO {

	private static final String INSERT_QUERY = "INSERT INTO Param(name, host, type, time, value) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_BY_NAME_QUERY = "SELECT value FROM Param WHERE name = ?";
	private static final String SELECT_ALL_QUERY = "SELECT time,value FROM Param WHERE name = ?";
	private static final String SELECT_ALL_STORAGE_QUERY = "SELECT name,time,value FROM Param";
	private static final String DELETE_BY_NAME_QUERY = "DELETE FROM Param WHERE name = ?";

	@Override
	public void save(Param param, ParamValueAbstract<?> paramValue, Date date, String host) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);

		try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);) {

			Gson gson = new Gson();
			Object value1 = paramValue;
			// ParamValueAbstract<?> value = gson.fromJson(gson.toJson(value1),
			// paramValue.getClass());

			String json = gson.toJson(value1);

			preparedStatement.setString(1, param.getName());
			preparedStatement.setString(2, host);
			preparedStatement.setString(3, param.getType().name());
			preparedStatement.setString(4, currentTime);
			preparedStatement.setString(5, json);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteByName(Param param) {

		try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME_QUERY);) {

			preparedStatement.setString(1, param.getName());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<ParamValueAbstract<?>> findByName(Param param) {

		List<ParamValueAbstract<?>> foundParams = new ArrayList<>();
		Gson gson = new Gson();

		try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME_QUERY);) {

			preparedStatement.setString(1, param.getName());
			ResultSet resultSet = preparedStatement.executeQuery();

			List<String> jsonList = new ArrayList<>();
			while (resultSet.next()) {
				jsonList.add(resultSet.getString("value"));
			}

			for (String s : jsonList) {
				Object paramValue = gson.fromJson(s, param.getParamClass());
				// ParamValueAbstract<?> paramValue = gson.fromJson(s,
				// param.getParamClass());
				foundParams.add((ParamValueAbstract<?>) paramValue);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return foundParams;

	}

	@Override
	public Map<Date, ParamValueAbstract<?>> findAll(Param param) {

		Map<Date, ParamValueAbstract<?>> foundParams = new HashMap<>();
		Gson gson = new Gson();

		try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);) {

			preparedStatement.setString(1, param.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				String value = resultSet.getString("value");

				Date date = resultSet.getTimestamp("time");

				Object paramValue = gson.fromJson(value, param.getParamClass());

				// ParamValueAbstract<?> paramValue = gson.fromJson(value,
				// param.getParamClass());
				foundParams.put(date, (ParamValueAbstract<?>) paramValue);
			}

			return foundParams;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Map<Date, ParamValueAbstract<?>> getAll() {

		Param param = null;
		List<ParamObtainer<?>> obtainers = ObtainerRegistry.getObtainers();

		Map<Date, ParamValueAbstract<?>> foundParams = new HashMap<>();
		Gson gson = new Gson();

		try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STORAGE_QUERY);) {

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				String value = resultSet.getString("value");
				String name = resultSet.getString("name");

				for (ParamObtainer<?> paramObtainer : obtainers) {

					if (name.equals(paramObtainer.getParamInfo().getName())) {
						param = paramObtainer.getParamInfo();
					}

				}

				Date date = resultSet.getTimestamp("time");

				Class<?> class2 = param.getParamClass();

				Object paramValue = gson.fromJson(value, class2);
				// ParamValueAbstract<?> paramValue = gson.fromJson(value,
				// class2);
				foundParams.put(date, (ParamValueAbstract<?>) paramValue);
			}

			return foundParams;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
