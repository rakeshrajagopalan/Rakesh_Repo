package samplepack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Session2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		String requestAttribute = (String) request.getAttribute("jsessionid");
		PrintWriter writer = response.getWriter();
		writer.println("<HTML> <BODY> <H1> Look at the URL in the title bar! "
				+ requestAttribute + "</H1> </BODY> </HTML>");
	}
}
