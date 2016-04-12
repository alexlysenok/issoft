package by.issoft.paramCollectorWeb.servlets;

import org.by.issoft.paramCollector.sockets.Server;

import org.by.issoft.paramCollector.ComputerParamsControleService;

public class CollectionExecutor {

	public void start() {
		Server server = new Server();
		server.start();

		ComputerParamsControleService service = new ComputerParamsControleService();
		service.startCollecting();
	}
}
