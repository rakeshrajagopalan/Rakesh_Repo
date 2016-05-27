package samplepack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DogServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		Dog dog = (Dog) getServletContext().getAttribute("dog");
		writer.println("<HTML><BODY> <H1><CENTER>The Dog's breed is: "
				+ dog.getBreed() + "</CENTER></H1></BODY></HTML>");
	}
}
