package by.issoft.paramCollectorWeb.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.by.issoft.paramCollector.ComputerParamsControleService;
import org.by.issoft.paramCollector.sockets.Server;

@WebListener
public class Main implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Server server = new Server();
		server.start();

		ComputerParamsControleService service = ComputerParamsControleService.getInstance();
		service.startCollecting();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
