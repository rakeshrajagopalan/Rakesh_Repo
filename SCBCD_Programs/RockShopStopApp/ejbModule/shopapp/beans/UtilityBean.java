package shopapp.beans;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import shopapp.entities.*;

@Stateless
@SuppressWarnings("unchecked")
public class UtilityBean implements UtilityRemote {

	@PersistenceContext(unitName = "RockShopStopApp")
	private EntityManager entityManager;

	private int[] transactionID;

	@Override
	public List fetchCategories() throws Exception {
		List returnList = new ArrayList();
		List catNameList = new ArrayList();
		Query query = entityManager.createNativeQuery(
				"SELECT DISTINCT(CATEGORY_NAME), A.CATEGORY_ID "
						+ "FROM CATEGORY_SD_TABLE A,"
						+ "ITEM_SD_TABLE B, WHERE A.CATEGORY_ID="
						+ "B.CATEGORY_ID", CategoryFetcher.class);
		List categories = query.getResultList();
		for (Object category : categories) {
			List itemNameList = new ArrayList();
			CategoryFetcher fetcher = (CategoryFetcher) category;
			catNameList.add(fetcher.getCategoryName());
			Integer catID = fetcher.getCategoryID();
			query = entityManager.createNativeQuery("SELECT * FROM "
					+ "ITEM_SD_TABLE A,CATEGORY_SD_TABLE B "
					+ "WHERE A.CATEGORY_ID=:catID AND "
					+ "A.CATEGORY_ID=B.CATEGORY_ID", ItemFetcher.class);
			query.setParameter("catID", catID);
			List relatedItemList = query.getResultList();
			for (Object o : relatedItemList) {
				itemNameList.add(o);
			}
			catNameList.add(itemNameList);
		}
		returnList.add(catNameList);
		return returnList;
	}

	public List<ItemFetcher> getItems() throws Exception {
		List<ItemFetcher> returnList = new ArrayList<ItemFetcher>();
		Query query = entityManager.createNativeQuery(
				"SELECT * FROM ITEM_SD_TABLE", ItemFetcher.class);
		returnList = query.getResultList();
		return returnList;
	}

	@Override
	public UserDetails validateLogin(String userName, String password)
			throws Exception {
		UserDetails userDetails = null;
		try {
			Query query = entityManager.createNativeQuery(
					"SELECT * FROM USER_TABLE WHERE USER_NAME=:userName "
							+ "AND USER_PASSWORD=:password", UserDetails.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			userDetails = (UserDetails) query.getSingleResult();
		} catch (NoResultException ex) {
			userDetails = null;
		}
		return userDetails;
	}

	@Override
	public void registerNewUser(UserDetails newUserDetails) throws Exception {
		entityManager.merge(newUserDetails);
	}

	@Override
	public boolean verifyUsernameExists(String userName) throws Exception {
		boolean userNameExists = false;
		try {
			UserDetails userDetails = (UserDetails) entityManager.find(
					UserDetails.class, userName);
			if (userDetails != null) {
				userNameExists = true;
			}
		} catch (NoResultException ex) {
			userNameExists = false;
		}
		return userNameExists;
	}

	public ItemTechDetailsFetcher getTechDetailsOfItem(int itemID)
			throws Exception {
		ItemTechDetailsFetcher returnItem = entityManager.find(
				ItemTechDetailsFetcher.class, itemID);
		return returnItem;
	}

	@Override
	public List<CartTrakker> fetchUserCartDetails(String userName)
			throws Exception {
		Query query = entityManager.createNativeQuery(
				"SELECT * FROM CART_ITEMS WHERE USER_NAME=:userName",
				CartTrakker.class);
		query.setParameter("userName", userName);
		List<CartTrakker> result = (List<CartTrakker>) query.getResultList();
		return result;
	}

	public void clearCart(String userName) throws Exception {
		Query query = entityManager
				.createNativeQuery("DELETE FROM CART_ITEMS WHERE USER_NAME=:userName");
		query.setParameter("userName", userName);
		query.executeUpdate();
	}

	public void addToCart(CartTrakker cartItems) throws Exception {
		entityManager.persist(cartItems);
	}

	public void removeFromCart(CartTrakker cartItem) throws Exception {
		CartPrimaryKey primKey = cartItem.getCartPK();
		Query query = entityManager
				.createNativeQuery("DELETE FROM CART_ITEMS WHERE TRANSACTION_ID=:transactionID AND CART_SER_NO=:cartSerNo");
		query.setParameter("transactionID", primKey.getTransactionID());
		query.setParameter("cartSerNo", primKey.getCartSerNum());
		query.executeUpdate();
	}

	public int[] configureTransactionID() throws Exception {
		Query query = entityManager
				.createNativeQuery("SELECT MAX(TRANS_HIST.TRANSACTION_ID) FROM TRANSACTION_HISTORY AS TRANS_HIST");
		transactionID = new int[2];
		Integer transID = (Integer) query.getSingleResult();
		if (transID == null || transID == 0) {
			transactionID[0] = 1;
		} else {
			transactionID[0] = transID + 1;
		}
		query = entityManager
				.createNativeQuery("SELECT MAX(CART.CART_SER_NO) FROM CART_ITEMS AS CART");
		Integer cartSerNum = (Integer) query.getSingleResult();
		if (cartSerNum == null || cartSerNum == 0) {
			transactionID[1] = 1;
		} else {
			transactionID[1] = cartSerNum + 1;
		}
		return transactionID;
	}

}
