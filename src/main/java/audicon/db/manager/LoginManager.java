package audicon.db.manager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import audicon.db.entity.UserEntity;
import audicon.db.manager.base.BaseDBManager;

public class LoginManager extends BaseDBManager {
	public LoginManager() {
		super();
	}
	
	public boolean login(final String username, final String password) {
		Session session = sessionFactory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cr.from(UserEntity.class);
		cr.select(root).where(cb.like(root.get("username"), username));
 
		return session.createQuery(cr).getResultList()!=null;
	}
}
