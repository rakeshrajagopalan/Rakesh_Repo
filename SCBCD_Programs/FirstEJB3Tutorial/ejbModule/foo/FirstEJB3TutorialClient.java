package foo;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstEJB3TutorialClient {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", "localhost:1099");
		Context context;
		try {
			context = new InitialContext(properties);
			BookTestBeanRemote beanRemote = (BookTestBeanRemote) context
					.lookup(BookTestBean.RemoteJNDIName);
			beanRemote.test();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
