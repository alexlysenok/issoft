package org.by.issoft.paramCollector;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import org.by.issoft.paramCollector.dao.DataBaseConnection;
import org.by.issoft.paramCollector.dao.ParamDAO;
import org.by.issoft.paramCollector.dao.ParamDAOFactory;
import org.by.issoft.paramCollector.dataStorage.MemoryStorage;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.ParamValue;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.ScalarParamValue;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.postgresql.util.PGobject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sun.util.logging.resources.logging_zh_HK;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		ParamDAO dao = new ParamDAOFactory().getParamDAO();
		Connection connection = null;
		Savepoint savepoint = null;
		try {
			connection = DataBaseConnection.getConnection();
			connection.setAutoCommit(false);

			PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();
			obtainer.getParamInfo().setName("test");
			PhysicalMemoryUsageValue value = new PhysicalMemoryUsageValue(100L);

			// dao.save(obtainer.getParamInfo(), value, new Date());

			dao.deleteByName(obtainer.getParamInfo());
			connection.rollback();

		} catch (SQLException e) {

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
