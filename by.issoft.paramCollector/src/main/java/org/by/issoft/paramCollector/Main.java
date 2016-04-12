package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.dao.ParamDAO;
import org.by.issoft.paramCollector.sockets.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author AlexeyLysenok
 *
 **/

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Server server = (Server) context.getBean("server");
		server.start();

		ParamsControleService service = context.getBean(ParamsControleService.class);
		service.startCollecting();

		// ParamDAO dao = context.getBean(ParamDAO.class);
		// System.out.println(dao.getAll().size());
		// System.out.println(dao.getAll());

	}

}
