package by.issoft.paramCollectorWeb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import org.by.issoft.paramCollector.ComputerParamsControleService;

public class Start extends AbstractHandler {

	public static void main(String[] args) throws Exception {

		new CollectionExecutor().start();

		Server server = new Server(8086);
		server.setHandler(new Start());

		server.start();
		server.join();
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");

	}

}
