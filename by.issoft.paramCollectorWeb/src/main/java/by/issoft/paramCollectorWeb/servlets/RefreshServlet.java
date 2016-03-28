package by.issoft.paramCollectorWeb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.by.issoft.paramCollector.ComputerParamsControleService;
import org.by.issoft.paramCollector.ParamCollector;
import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.xml.ParamToCollect;

@WebServlet("/reload")
public class RefreshServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("buttonID");
		String[] words = id.split("-");

		ParamValueAbstract<?> value = null;

		ParamToCollect paramToCollect = new ParamToCollect();

		List<ParamCollector> threads = ComputerParamsControleService.getInstance().getThreads();
		for (ParamCollector paramCollector : threads) {

			if (paramCollector.getCollectingParam().getParamName().equals(words[0]) && paramCollector.getCollectingParam().getHost().equals(paramToCollect.convertToRealHost(words[1]))
					&& String.valueOf(paramCollector.getCollectingParam().getFrequency()).equals(words[2])) {
				value = paramCollector.collect();

			} else {
				// System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
			}
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(value.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
