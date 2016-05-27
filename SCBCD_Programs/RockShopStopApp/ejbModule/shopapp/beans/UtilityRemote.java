package shopapp.beans;

import java.util.*;
import javax.ejb.*;
import shopapp.entities.*;

@Remote
@SuppressWarnings("unchecked")
public interface UtilityRemote {

	public List fetchCategories() throws Exception;

	public UserDetails validateLogin(String userName, String password)
			throws Exception;

	public void registerNewUser(UserDetails newUserDetails) throws Exception;

	public boolean verifyUsernameExists(String userName) throws Exception;

	public List<ItemFetcher> getItems() throws Exception;

	public ItemTechDetailsFetcher getTechDetailsOfItem(int itemID)
			throws Exception;

	public List<CartTrakker> fetchUserCartDetails(String userName)
			throws Exception;

	public void clearCart(String userName) throws Exception;

	public void addToCart(CartTrakker cartItem) throws Exception;

	public int[] configureTransactionID() throws Exception;

	public void removeFromCart(CartTrakker cartItem) throws Exception;

}
