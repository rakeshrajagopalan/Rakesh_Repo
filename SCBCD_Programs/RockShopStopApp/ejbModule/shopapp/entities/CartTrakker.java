package shopapp.entities;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "CART_ITEMS")
public class CartTrakker implements Serializable {

	private static final long serialVersionUID = 3468026489784306092L;

	private String userName;

	private Integer itemID;

	private String itemName;

	private Integer quantityReqd;

	private Integer price;

	private CartPrimaryKey cartPK;

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "transactionID", column = @Column(name = "TRANSACTION_ID")),
			@AttributeOverride(name = "cartSerNum", column = @Column(name = "CART_SER_NO")) })
	public CartPrimaryKey getCartPK() {
		return cartPK;
	}

	public void setCartPK(CartPrimaryKey cartPK) {
		this.cartPK = cartPK;
	}

	@Column(name = "ITEM_ID")
	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "ITEM_NAME")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "QUANTITY_REQD")
	public Integer getQuantityReqd() {
		return quantityReqd;
	}

	public void setQuantityReqd(Integer quantityReqd) {
		this.quantityReqd = quantityReqd;
	}

	@Column(name = "PRICE")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
