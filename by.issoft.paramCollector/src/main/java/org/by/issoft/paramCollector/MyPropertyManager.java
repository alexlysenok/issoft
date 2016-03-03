package org.by.issoft.paramCollector;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Get the neccessery urls from url.properties file.
 */

public class MyPropertyManager {
	private static Properties properties = new Properties();

	public static String getProperty(String name) {
		String string = "";
		try (FileReader in = new FileReader("urls.properties");) {
			properties.load(in);
			string = properties.getProperty(name);
		} catch (IOException e) {

			e.printStackTrace();
			throw new RuntimeException();
		}
		return string;
	}

	public static String getJDBCProperty(String name) {
		String string = "";
		try (FileReader in = new FileReader("jdbc.properties");) {
			properties.load(in);
			string = properties.getProperty(name);
		} catch (IOException e) {

			e.printStackTrace();
			throw new RuntimeException();
		}
		return string;
	}

}
