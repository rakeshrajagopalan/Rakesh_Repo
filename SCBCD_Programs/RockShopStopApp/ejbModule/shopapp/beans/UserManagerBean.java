package shopapp.beans;

import java.sql.*;
import java.util.*;

import javax.ejb.*;
import javax.jms.*;
import javax.jms.Connection;
import javax.jms.Queue;
import javax.persistence.*;
import shopapp.entities.*;
import shopapp.utils.*;

@SuppressWarnings("unchecked")
@Stateful
public class UserManagerBean implements UserManagerRemote {

	@PersistenceContext(unitName = "RockShopStopApp")
	private EntityManager entityManager;

	private UserDetails currentUserDetails;

	private List<CartTrakker> currentUserCart;

	public void setCurrentUser(String userName) throws Exception {
		currentUserDetails = (UserDetails) entityManager.find(
				UserDetails.class, userName);
	}

	@Remove
	public void removeCurrentUser() throws Exception {
		this.currentUserDetails = null;
		List<CartTrakker> temp = new ArrayList<CartTrakker>();
		for (CartTrakker item : currentUserCart) {
			temp.add(item);
		}
		for (CartTrakker item : temp) {
			removeFromCart(item);
		}
		temp = null;
		currentUserCart = null;
	}

	public void updateUserDetails(Map<String, String> userDetails)
			throws Exception {
		String firstName = userDetails.get("firstname");
		String lastName = userDetails.get("lastname");
		String address = userDetails.get("address");
		String emailID = userDetails.get("emailid");
		currentUserDetails.setFirstName(firstName);
		currentUserDetails.setLastName(lastName);
		currentUserDetails.setUserAddress(address);
		currentUserDetails.setEmailID(emailID);
		currentUserDetails = entityManager.merge(currentUserDetails);
	}

	public UserDetails getCurrentUser() throws Exception {
		return currentUserDetails;
	}

	public void changePassword(String newPassword) throws Exception {
		currentUserDetails.setUserPassword(newPassword);
		currentUserDetails = entityManager.merge(currentUserDetails);
	}

	@Override
	public void doCartMigration() throws Exception {
		Query query = entityManager
				.createNativeQuery(
						"UPDATE CART_ITEMS SET USER_NAME=:userName WHERE USER_NAME=:currentUser",
						CartTrakker.class);
		query.setParameter("userName", currentUserDetails.getUserName());
		query.setParameter("currentUser",
				ApplicationConstants.INITIAL_USER_NAME);
		query.executeUpdate();
		query = entityManager.createNativeQuery(
				"SELECT * FROM CART_ITEMS WHERE USER_NAME=:userName",
				CartTrakker.class);
		query.setParameter("userName", currentUserDetails.getUserName());
		List<CartTrakker> result = (List<CartTrakker>) query.getResultList();
		this.currentUserCart = result;
	}

	@Override
	public List<CartTrakker> getCurrentCart() throws Exception {
		return currentUserCart;
	}

	public void addToCart(CartTrakker cartItems) throws Exception {
		entityManager.persist(cartItems);
		if (currentUserCart == null) {
			currentUserCart = new ArrayList<CartTrakker>();
			currentUserCart.add(cartItems);
		} else {
			currentUserCart.add(cartItems);
		}
	}

	public void removeFromCart(CartTrakker cartItem) throws Exception {
		CartPrimaryKey primKey = cartItem.getCartPK();
		int indexToRemove = -1;
		Query query = entityManager
				.createNativeQuery("DELETE FROM CART_ITEMS WHERE TRANSACTION_ID=:transactionID AND CART_SER_NO=:cartSerNo");
		query.setParameter("transactionID", primKey.getTransactionID());
		query.setParameter("cartSerNo", primKey.getCartSerNum());
		query.executeUpdate();
		for (int index = 0; index < currentUserCart.size(); index++) {
			CartTrakker temp = currentUserCart.get(index);
			CartPrimaryKey tempPrimKey = temp.getCartPK();
			if (tempPrimKey.getTransactionID().equals(
					primKey.getTransactionID())
					&& tempPrimKey.getCartSerNum().equals(
							primKey.getCartSerNum())) {
				indexToRemove = index;
				break;
			}
		}
		currentUserCart.remove(indexToRemove);
	}

	public void clearCart() throws Exception {
		currentUserCart = null;
	}

	public void completeTransaction(List<CartTrakker> cartItems, int totalPrice)
			throws Exception {
		currentUserDetails.setCreditPoints(currentUserDetails.getCreditPoints()
				- totalPrice);
		currentUserDetails = entityManager.merge(currentUserDetails);
		int transactionID = cartItems.get(0).getCartPK().getTransactionID();
		String userName = currentUserDetails.getUserName();
		String itemIDs = "";
		String itemNames = "";
		for (CartTrakker trakker : cartItems) {
			int quantityReqd = trakker.getQuantityReqd();
			int itemNum = trakker.getItemID();
			itemIDs += itemNum + ",";
			itemNames += trakker.getItemName() + ",";
			ItemFetcher fetcher = entityManager
					.find(ItemFetcher.class, itemNum);
			fetcher.setRemainingNum(fetcher.getRemainingNum() - quantityReqd);
			entityManager.merge(fetcher);
			removeFromCart(trakker);
		}
		TransactionHistory history = new TransactionHistory();
		history.setTransactionID(transactionID);
		history.setUserName(userName);
		history.setItemID(itemIDs);
		history.setItemName(itemNames);
		history.setTransactionPrice(totalPrice);
		history.setTransactionTimestamp(new Timestamp(System
				.currentTimeMillis()));
		entityManager.persist(history);
		sendCourierOrder(itemNames);
	}

	private void sendCourierOrder(String itemNames) throws Exception {
		ConnectionFactory connectionFactory = (ConnectionFactory) UtilityClass
				.lookUpBeans("ConnectionFactory");
		Queue courierQueue = (Queue) UtilityClass
				.lookUpBeans("queue/RakeshQueue");
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(courierQueue);
		ObjectMessage message = session.createObjectMessage();
		ArrayList<String> currUserDetails = new ArrayList<String>();
		currUserDetails.add(currentUserDetails.getFirstName());
		currUserDetails.add(currentUserDetails.getLastName());
		currUserDetails.add(currentUserDetails.getUserAddress());
		currUserDetails.add(currentUserDetails.getUserCountry());
		currUserDetails.add(currentUserDetails.getUserState());
		currUserDetails.add(currentUserDetails.getUserCity());
		currUserDetails.add(currentUserDetails.getUserPincode());
		currUserDetails.add(itemNames);
		message.setObject(currUserDetails);
		producer.send(message);
		producer.close();
		session.close();
		connection.close();
	}

}
