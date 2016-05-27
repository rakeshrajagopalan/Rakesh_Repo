package entitybeantest;

import javax.ejb.Remote;

@Remote
public interface LoginRemote {
	boolean validate(String userName, String password);
	void save(EntityBeanClass entityClass);
}
