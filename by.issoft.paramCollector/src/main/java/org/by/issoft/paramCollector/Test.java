package org.by.issoft.paramCollector;

import java.util.List;

import org.by.issoft.paramCollector.dao.ParamDAO;
import org.by.issoft.paramCollector.dao.ParamDAOFactory;
import org.by.issoft.paramCollector.paramObtainers.FreePhysicalMemoryObtainer;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

public class Test {

	public static void main(String[] args) {
		FreePhysicalMemoryObtainer obtainer = new FreePhysicalMemoryObtainer();

		ParamDAO dao = new ParamDAOFactory().getParamDAO();

		List<ParamValueAbstract<?>> found = dao.findByName(obtainer.getParamInfo());
		System.out.println(found);
	}
}
