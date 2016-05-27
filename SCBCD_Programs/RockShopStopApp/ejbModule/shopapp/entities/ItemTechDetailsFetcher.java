package shopapp.entities;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "ITEM_SD_TECHNICAL_DETAILS")
public class ItemTechDetailsFetcher implements Serializable {

	private static final long serialVersionUID = -635783843769567923L;

	private Integer itemID;

	private String itemMake;

	private String itemCapacity;

	private String itemDesc;

	private String itemColors;

	@Id
	@Column(name = "ITEM_ID")
	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	@Column(name = "ITEM_MAKE")
	public String getItemMake() {
		return itemMake;
	}

	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}

	@Column(name = "ITEM_CAPACITY")
	public String getItemCapacity() {
		return itemCapacity;
	}

	public void setItemCapacity(String itemCapacity) {
		this.itemCapacity = itemCapacity;
	}

	@Column(name = "ITEM_DESCRIPTION")
	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	@Column(name = "AVAIL_COLORS")
	public String getItemColors() {
		return itemColors;
	}

	public void setItemColors(String itemColors) {
		this.itemColors = itemColors;
	}

}
