package samplepack;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class SessionAttributeListenerTest extends HttpServlet implements
		HttpSessionAttributeListener {

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		arg0.setAttribute("foo", "a");
		arg0.setAttribute("foo", "b");
		arg0.removeAttribute("foo");
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println(" Added: " + arg0.getName() + "-> "
				+ arg0.getValue());
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println(" Removed: " + arg0.getName() + "-> "
				+ arg0.getValue());
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println(" Replaced: " + arg0.getName() + "-> "
				+ arg0.getValue());
	}

}
