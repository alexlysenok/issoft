package by.issoft.paramCollectorWeb.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.ProtectionDomain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRunner extends AbstractHandler {

	public static void main(String[] args) throws Exception {
		startServer();
	}

	public static void startServer() {

		try {
			Server server = new Server();

			ServerConnector connector = new ServerConnector(server);
			connector.setPort(8080);

			server.setConnectors(new Connector[] { connector });

			WebAppContext context = new WebAppContext();
			context.setServer(server);
			context.setContextPath("/");
			context.setThrowUnavailableOnStartupException(true);

			ProtectionDomain protectionDomain = JettyRunner.class.getProtectionDomain();
			URL location = protectionDomain.getCodeSource().getLocation();
			//
			context.setWar(location.toExternalForm());
			//
			server.setHandler(context);
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void startServer2() {

		try {
			String webappDirLocation = "src/main/webapp/";

			Server server = new Server(8080);
			WebAppContext root = new WebAppContext();

			root.setContextPath("/");
			root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
			root.setResourceBase(webappDirLocation);

			root.setParentLoaderPriority(true);

			server.setHandler(root);

			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");

	}

}
