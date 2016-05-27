package samplepack;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DogContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		context.removeAttribute("dog");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		String breed = context.getInitParameter("breed");
		Dog dog = new Dog();
		dog.setBreed(breed);
		context.setAttribute("dog", dog);
	}
	
}
