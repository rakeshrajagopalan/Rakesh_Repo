package entitybeantest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LoginValidator implements LoginRemote {

	@PersistenceContext(unitName = "LoginCountApp")
	private EntityManager entityManager;

	public boolean validate(String userName, String password) {
		boolean correctLogin = false;
		if (userName.equals("Rakesh") && password.equals("Rakesh")) {
			correctLogin = true;
		}
		return correctLogin;
	}

	public void save(EntityBeanClass entityClass) {
		entityManager.persist(entityClass);
	}
}
