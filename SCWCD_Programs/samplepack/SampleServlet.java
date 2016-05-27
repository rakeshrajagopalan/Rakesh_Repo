package samplepack;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		writer.println("<HTML> <BODY> Servlet Config:"
				+ getServletConfig().getInitParameter("email")
				+ "Servlet Context:"
				+ getServletContext().getInitParameter("email")
				+ "</BODY> </HTML>");

	}
}
