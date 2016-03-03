package org.by.issoft.paramCollector;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.by.issoft.paramCollector.dao.DataBaseConnection;
import org.by.issoft.paramCollector.dao.ParamDAO;
import org.by.issoft.paramCollector.dao.ParamDAOFactory;
import org.by.issoft.paramCollector.paramObtainers.PhysicalMemoryUsageObtainer;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataBaseStorageTest {

	ParamDAO dao = new ParamDAOFactory().getParamDAO();
	Connection connection;

	@Before
	public void connectDatabase() {

		try {
			connection = DataBaseConnection.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void saveFindTest() {
		PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();
		obtainer.getParamInfo().setName("test");
		PhysicalMemoryUsageValue value = new PhysicalMemoryUsageValue(100L);

		// save and find
		dao.save(obtainer.getParamInfo(), value, new Date());
		assertEquals("the hashcode must be the same ", value, dao.findByName(obtainer.getParamInfo()).get(0));
	}

	@Test
	public void deleteTest() {
		PhysicalMemoryUsageObtainer obtainer = new PhysicalMemoryUsageObtainer();
		obtainer.getParamInfo().setName("test");

		// delete
		dao.deleteByName(obtainer.getParamInfo());
		List<ParamValueAbstract<?>> found = dao.findByName(obtainer.getParamInfo());
		assertEquals("the list must be empty", 0, found.size());
	}

	@After
	public void rollBack() {
		try {
			// connection.rollback();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
