package shopapp.beans;

import java.util.*;

import javax.ejb.*;
import shopapp.entities.*;

@Remote
public interface UserManagerRemote {

	public void setCurrentUser(String userName) throws Exception;

	public UserDetails getCurrentUser() throws Exception;

	public void removeCurrentUser() throws Exception;

	public void updateUserDetails(Map<String, String> userDetails)
			throws Exception;

	public void changePassword(String newPassword) throws Exception;

	public void doCartMigration() throws Exception;

	public List<CartTrakker> getCurrentCart() throws Exception;

	public void addToCart(CartTrakker cartItems) throws Exception;

	public void removeFromCart(CartTrakker cartItem) throws Exception;

	public void clearCart() throws Exception;

	public void completeTransaction(List<CartTrakker> cartItems, int totalPrice) throws Exception;
	
}
