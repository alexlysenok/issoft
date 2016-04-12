package org.by.issoft.paramCollector.xml;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.*;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class MyXMLParser implements MyParser {

	@Override
	public ParamsToCollect parse(String url) {

		ParamsToCollect paramsToCollect = null;

		try (InputStream in = new FileInputStream(url)) {
			XStream xstream = new XStream(new DomDriver());
			xstream.alias("ParamsToCollect", ParamsToCollect.class);
			xstream.alias("ParamToCollect", ParamToCollect.class);
			xstream.addImplicitCollection(ParamsToCollect.class, "params");

			paramsToCollect = (ParamsToCollect) xstream.fromXML(in);

			List<ParamToCollect> list = paramsToCollect.getParams();
			Set<ParamToCollect> set = new HashSet<>();
			set.addAll(list);
			list.clear();
			list.addAll(set);

			for (ParamToCollect paramToCollect : paramsToCollect.getParams()) {
				paramToCollect.setHostWithoutDots(paramToCollect.getHost());
			}

			return paramsToCollect;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
