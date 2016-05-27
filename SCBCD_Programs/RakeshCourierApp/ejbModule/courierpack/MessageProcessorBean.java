package courierpack;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageProcessorBean implements MessageProcessorInterface {

	@PersistenceContext(unitName = "RockCourierApp")
	private EntityManager entityManager;

	public void processMessage(List<String> courierDetails) {
		String transNumber = "";
		String firstName = courierDetails.get(0);
		String lastName = courierDetails.get(1);
		String address = courierDetails.get(2);
		String country = courierDetails.get(3);
		String state = courierDetails.get(4);
		String city = courierDetails.get(5);
		String pincode = courierDetails.get(6);
		String items = courierDetails.get(7);
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
		transNumber = transNumber + formatter.format(timeStamp);
		transNumber = (formatter.format(timeStamp) + country.substring(0, 2)
				+ state.substring(0, 2) + city.substring(0, 3)
				+ pincode.substring(pincode.length() - 3)
				+ firstName.substring(0, 3) + lastName.substring(0, 3))
				.toUpperCase();
		System.out.println("The transaction number is: " + transNumber);
		CourierEntity entity = new CourierEntity();
		entity.setTransactionNumber(transNumber);
		entity.setCustomerName(firstName + " " + lastName);
		entity.setCustomerAddress(address + " " + city + " - " + pincode + " "
				+ state + " " + country);
		entity.setItemsList(items);
		entity.setCourierPrice("0");
		entityManager.merge(entity);
	}

}
