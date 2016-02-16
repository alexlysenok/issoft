package org.by.issoft.paramCollector;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
}
