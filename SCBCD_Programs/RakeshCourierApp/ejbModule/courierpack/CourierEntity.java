package courierpack;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURIER_DETAILS")
public class CourierEntity implements Serializable {
	
	private static final long serialVersionUID = 8328671732098924590L;

	private String transactionNumber;
	
	private String customerName;
	
	private String customerAddress;
	
	private String itemsList;
	
	private String courierPrice;

	@Id
	@Column(name="COURIER_TRANS_NUMBER")
	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Column(name="CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name="CUSTOMER_ADDRESS")
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Column(name="ITEMS_LIST")
	public String getItemsList() {
		return itemsList;
	}

	public void setItemsList(String itemsList) {
		this.itemsList = itemsList;
	}

	@Column(name="COURIER_PRICE")
	public String getCourierPrice() {
		return courierPrice;
	}

	public void setCourierPrice(String courierPrice) {
		this.courierPrice = courierPrice;
	}
	
	
}
