package org.by.issoft.paramCollector;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import org.by.issoft.paramCollector.dao.DataBaseConnection;

public class DataBaseConnectionTest {
	@Test
	public void testSQLconnection() {
		assertNotNull(DataBaseConnection.getConnection());
	}
}
