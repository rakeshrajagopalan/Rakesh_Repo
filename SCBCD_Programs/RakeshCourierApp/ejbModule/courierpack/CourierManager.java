package courierpack;

import java.util.*;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

@MessageDriven(name = "MessageConsumerBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/RakeshQueue") })
@SuppressWarnings(value = { "unchecked" })
public class CourierManager implements MessageListener {
	public void onMessage(Message arg0) {
		try {
			ObjectMessage message = (ObjectMessage) arg0;
			ArrayList<String> courierDetails = (ArrayList<String>) message
					.getObject();
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			prop.put(Context.URL_PKG_PREFIXES,
					" org.jboss.naming:org.jnp.interfaces");
			prop.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			Context jndiContext = new InitialContext(prop);
			MessageProcessorInterface messageProc = (MessageProcessorInterface) jndiContext
					.lookup("MessageProcessorBean/remote");
			messageProc.processMessage(courierDetails);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
