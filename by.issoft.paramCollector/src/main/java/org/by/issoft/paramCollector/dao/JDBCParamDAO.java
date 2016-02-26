package org.by.issoft.paramCollector.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.by.issoft.paramCollector.MyClass;
import org.by.issoft.paramCollector.MyPropertyManager;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.Param;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.postgresql.util.PGobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

public class JDBCParamDAO implements ParamDAO {

	private static final String INSERT_QUERY = "INSERT INTO Param(name, type, time, value) VALUES (?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT value FROM Param WHERE name = ?";

	@Override
	public void save(Param param, ParamValue paramValue, Date date) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ParamValueAbstract<?> value = (ParamValueAbstract<?>) paramValue;
		String name = param.getName();
		String type = param.getType().name();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		try {
			connection = DataBaseConnection.getConnection();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(value);

			//
			preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, type);
			preparedStatement.setString(3, currentTime);
			preparedStatement.setString(4, json);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void save2(Param param, ParamValue paramValue, Date date) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ParamValueAbstract<?> value = (ParamValueAbstract<?>) paramValue;
		String name = param.getName();
		String type = param.getType().name();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		try {
			connection = DataBaseConnection.getConnection();

			MyClass obj = new MyClass(5, "jackson");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(obj);

			preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, type);
			preparedStatement.setString(3, currentTime);
			preparedStatement.setString(4, json);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void save3(Param param, ParamValue paramValue, Date date) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Object value = paramValue;
		// ParamValueAbstract value = (ParamValueAbstract) paramValue;
		String name = param.getName();
		String type = param.getType().name();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		try {
			connection = DataBaseConnection.getConnection();

			// MyClass obj = new MyClass(5, "jackson");
			PhysicalMemoryUsageValue value2 = new PhysicalMemoryUsageValue(100L);
			Gson gson = new Gson();
			String json = gson.toJson(value);

			preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, type);
			preparedStatement.setString(3, currentTime);
			preparedStatement.setString(4, json);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public ParamValue find(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		ObjectMapper mapper = new ObjectMapper();
		List<String> list = new ArrayList<>();

		try {
			connection = DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_QUERY);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("value"));
			}
			// System.out.println(list);

			// ParamValueAbstract<?> paramValue = mapper.readValue(list.get(0),
			// ParamValueAbstract.class);
			String json = list.get(0);
			System.out.println(json);

			// MyClass paramValue = mapper.readValue(json, MyClass.class);
			Gson gson = new Gson();
			// MyClass paramValue = gson.fromJson(json, MyClass.class);
			// PhysicalMemoryUsageValue paramValue = gson.fromJson(json,
			// PhysicalMemoryUsageValue.class);
			Object paramValue = gson.fromJson(json, Object.class);
			System.out.println(paramValue);
			// ParamValueAbstract paramValue = gson.fromJson(json,
			// ParamValueAbstract.class);
			ParamValue obj = (ParamValue) paramValue;

		} catch (Exception e) {

		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public static void main(String[] args) {
		JDBCParamDAO dao = new JDBCParamDAO();
		PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();
		// DisksInfoObtainer obtainer = new DisksInfoObtainer();
		// dao.save(obtainer.getParamInfo(), obtainer.getCurrentParamValue(),
		// new Date());
		// dao.save3(obtainer.getParamInfo(), obtainer.getCurrentParamValue(),
		// new Date());
		dao.find("USING_RAM");
	}

}
