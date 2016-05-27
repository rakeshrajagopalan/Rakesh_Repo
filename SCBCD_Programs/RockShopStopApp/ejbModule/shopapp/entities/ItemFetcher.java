package shopapp.entities;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "Rakesh.ITEM_SD_TABLE")
public class ItemFetcher implements Serializable {

	private static final long serialVersionUID = 2837420188954740714L;

	private Integer itemID;

	private Integer categoryID;

	private String itemName;

	private Integer itemPrice;

	private Integer remainingNum;

	@Id
	@Column(name = "ITEM_ID")
	public Integer getItemID() {
		return itemID;
	}

	@Column(name = "CATEGORY_ID")
	public Integer getCategoryID() {
		return categoryID;
	}

	@Column(name = "ITEM_NAME")
	public String getItemName() {
		return itemName;
	}

	@Column(name = "ITEM_PRICE")
	public Integer getItemPrice() {
		return itemPrice;
	}

	@Column(name = "NOS_REMAINING")
	public Integer getRemainingNum() {
		return remainingNum;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setRemainingNum(Integer remainingNum) {
		this.remainingNum = remainingNum;
	}

}
