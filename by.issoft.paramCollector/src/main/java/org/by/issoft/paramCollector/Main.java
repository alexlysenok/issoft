package org.by.issoft.paramCollector;

import org.by.issoft.paramCollector.sockets.Server;

/**
 * 
 * @author AlexeyLysenok
 *
 **/

public class Main {

	public static void main(String[] args) {

		Server server = new Server();
		server.start();

		ComputerParamsControleService service = ComputerParamsControleService.getInstance();
		service.startCollecting();

	}

}
