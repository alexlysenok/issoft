package by.issoft.paramCollectorWeb.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.annotation.XmlValue;

import org.by.issoft.paramCollector.sockets.Server;

import org.by.issoft.paramCollector.ComputerParamsControleService;

@WebListener
public class Main implements ServletContextListener {

	// @Value("${urls.xml}")
	// String urlXml;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Server server = new Server();
		server.start();

		ComputerParamsControleService service = new ComputerParamsControleService();
		service.startCollecting();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
