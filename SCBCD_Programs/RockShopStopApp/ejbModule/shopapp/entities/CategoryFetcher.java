package shopapp.entities;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "Rakesh.CATEGORY_SD_TABLE")
public class CategoryFetcher implements Serializable {

	private static final long serialVersionUID = 7503500805379746206L;

	private String categoryName;

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	private Integer categoryID;

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "CATEGORY_NAME")
	public String getCategoryName() {
		return categoryName;
	}

	@Id
	@Column(name = "CATEGORY_ID")
	public Integer getCategoryID() {
		return categoryID;
	}

}
