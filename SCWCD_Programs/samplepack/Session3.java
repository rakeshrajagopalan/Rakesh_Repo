package samplepack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Session3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		session.setAttribute("foo", "bar");
		session.setMaxInactiveInterval(1200);
		writer.println("<HTML><body>" + session.getAttribute("foo") + "</body></html>");
	}
}
