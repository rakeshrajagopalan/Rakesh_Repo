package entitybeantest;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "sesl.USER_LOGIN_TABLE")
public class EntityBeanClass implements Serializable {

	private static final long serialVersionUID = -9121269648935755547L;

	private Integer visitCount;

	private String name;

	@Column(name = "USER_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name="VISIT_COUNT")
	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

}
