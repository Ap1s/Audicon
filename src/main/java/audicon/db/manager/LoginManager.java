package audicon.db.manager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import audicon.db.entity.UserEntity;
import audicon.db.manager.base.BaseDBManager;

public class LoginManager extends BaseDBManager {
	public LoginManager() {
		super();
	}
	
	public UserEntity login(final String username, final String password) {
		Session session = sessionFactory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cr.from(UserEntity.class);
		
		Predicate usernamePredicate = cb.like(root.get("username"), username);
		Predicate passwordPredicate = cb.like(root.get("password"), password);
		
		Predicate finalPredicate = cb.and(usernamePredicate, passwordPredicate);
		
		cr.select(root).where(finalPredicate);
		
		try {
			int id = session.createQuery(cr).getResultList().get(0).getId();
			String uname = session.createQuery(cr).getResultList().get(0).getUsername();
			return new UserEntity(id, uname, null);
		}
		catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
}
