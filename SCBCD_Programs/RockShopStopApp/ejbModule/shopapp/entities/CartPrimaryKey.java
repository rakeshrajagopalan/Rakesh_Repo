package shopapp.entities;

import java.io.*;
import javax.persistence.*;

@Embeddable
public class CartPrimaryKey implements Serializable {

	private static final long serialVersionUID = 7294662792030477397L;

	private Integer transactionID;

	private Integer cartSerNum;

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getCartSerNum() {
		return cartSerNum;
	}

	public void setCartSerNum(Integer cartSerNum) {
		this.cartSerNum = cartSerNum;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof CartPrimaryKey) {
			CartPrimaryKey primKey = (CartPrimaryKey) obj;
			if (primKey.transactionID == this.transactionID
					&& primKey.cartSerNum == this.cartSerNum) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return (transactionID + cartSerNum);
	}

}
