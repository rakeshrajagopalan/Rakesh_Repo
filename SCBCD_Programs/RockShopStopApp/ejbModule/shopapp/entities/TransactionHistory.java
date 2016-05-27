package shopapp.entities;

import java.io.*;
import java.sql.*;
import javax.persistence.*;

@Entity
@Table(name = "Rakesh.TRANSACTION_HISTORY")
public class TransactionHistory implements Serializable {

	private static final long serialVersionUID = -1364364962573922140L;

	private Integer transactionID;

	private String itemID;

	private String userName;

	private String itemName;

	private Integer transactionPrice;

	private Timestamp transactionTimestamp;

	@Id
	@Column(name = "TRANSACTION_ID")
	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "ITEM_NAMES")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "TRANSACTION_PRICE")
	public Integer getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(Integer transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	@Column(name = "TRANS_TIMESTAMP")
	public Timestamp getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Timestamp transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	@Column(name = "ITEM_IDS")
	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

}
