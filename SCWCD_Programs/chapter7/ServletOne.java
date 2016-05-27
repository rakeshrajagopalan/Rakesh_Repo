package chapter7;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletOne extends HttpServlet {
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		getServletContext().setAttribute("hi", "hello");
		RequestDispatcher disp = arg0.getRequestDispatcher("/ServletTwo.do");
		disp.forward(arg0, arg1);
	}
}
