package by.issoft.paramCollectorWeb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.by.issoft.paramCollector.ComputerParamsControleService;
import org.by.issoft.paramCollector.ParamCollector;

@WebServlet("/")
public class StartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ParamCollector> threads;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ComputerParamsControleService service = new ComputerParamsControleService();
		threads = service.getThreads();
		ServletContext context = request.getServletContext();
		context.setAttribute("threads", threads);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
