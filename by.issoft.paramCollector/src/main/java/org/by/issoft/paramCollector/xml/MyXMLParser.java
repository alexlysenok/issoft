package org.by.issoft.paramCollector.xml;

import org.by.issoft.paramCollector.MyPropertyManager;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.*;
import java.io.*;

public class MyXMLParser {

	private final static String XML_URL = MyPropertyManager.getProperty("urls.xml");

	public static ParamsToCollect parseXML() {

		try (InputStream in = new FileInputStream(XML_URL)) {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("ParamsToCollect", ParamsToCollect.class);
			xstream.alias("ParamToCollect", ParamToCollect.class);
			xstream.addImplicitCollection(ParamsToCollect.class, "params");

			ParamsToCollect paramsToCollect = (ParamsToCollect) xstream.fromXML(in);

			return paramsToCollect;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
