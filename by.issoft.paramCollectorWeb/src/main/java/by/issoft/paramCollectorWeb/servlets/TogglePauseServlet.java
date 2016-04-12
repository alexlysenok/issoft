package by.issoft.paramCollectorWeb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.by.issoft.paramCollector.xml.ParamToCollect;

import org.by.issoft.paramCollector.ComputerParamsControleService;
import org.by.issoft.paramCollector.ParamCollector;

@WebServlet("/toggle")
public class TogglePauseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("buttonID");
		String[] words = id.split("-");

		String status = null;
		ParamToCollect paramToCollect = new ParamToCollect();

		ComputerParamsControleService service = new ComputerParamsControleService();
		List<ParamCollector> threads = service.getThreads();
		for (ParamCollector paramCollector : threads) {
			if (paramCollector.getCollectingParam().getParamName().equals(words[0]) && paramCollector.getCollectingParam().getHost().equals(paramToCollect.convertToRealHost(words[1]))
					&& String.valueOf(paramCollector.getCollectingParam().getFrequency()).equals(words[2])) {
				paramToCollect = paramCollector.getCollectingParam();
				service.tooglePause(paramToCollect);
				status = paramCollector.getStatus();
			} else {
				// System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
			}
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(status);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
