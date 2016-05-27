package samplepack;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ListenerTest extends HttpServlet implements
		ServletRequestAttributeListener {

	private static final long serialVersionUID = 209904657846827957L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("foo", "a");
		request.setAttribute("foo", "b");
		request.removeAttribute("foo");
	}

	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println(" Added: " + arg0.getName() + "-> "
				+ arg0.getValue());
	}

	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println(" Removed: " + arg0.getName() + "-> "
				+ arg0.getValue());
	}

	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println(" Replaced: " + arg0.getName() + "-> "
				+ arg0.getValue());
	}

}
