package chapter7;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class Count extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		Counter ctr = new Counter();
		ctr.deserialize();
		writer.println("<HTML> <BODY> <H1>The visit count is: ");
		writer.println(ctr.counter());
		writer.println("</H1> </BODY> </HTML> ");
		ctr.serialize();
	}

}
