package by.issoft.paramCollectorWebApp.servlets;

import org.by.issoft.paramCollector.dao.ParamDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		ParamDAO dao = context.getBean(ParamDAO.class);
		System.out.println(dao.getAll().size());
		System.out.println(dao.getAll());

	}
}
