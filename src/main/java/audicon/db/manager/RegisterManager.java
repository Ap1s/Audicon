package audicon.db.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import audicon.db.entity.UserEntity;
import audicon.db.manager.base.BaseDBManager;
import audicon.functional.bo.User;

public class RegisterManager extends BaseDBManager {
	
	public RegisterManager() {
		super();
	}

	public void insert(final User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userEntity);
		session.getTransaction().commit();
		session.close();
	}
}
