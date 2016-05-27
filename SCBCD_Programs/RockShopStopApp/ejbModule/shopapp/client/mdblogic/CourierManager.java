package shopapp.client.mdblogic;

import javax.ejb.*;
import javax.jms.*;
import shopapp.entities.*;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = 
	"destinationName", propertyValue = "queue/RakeshQueue") })
public class CourierManager implements MessageListener {

	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			UserDetails userDetails = (UserDetails) objectMessage.getObject();
			new ShowGUI(userDetails).setVisible(true);
		} catch (JMSException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
