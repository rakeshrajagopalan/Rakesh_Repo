package samplepack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Session1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<HTML> <BODY> <A HREF = ' "
				+ response.encodeURL("Session2.do") + "'> Click Me: s</A> </BODY> </HTML>");
	}
}
