package audicon.db.manager;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import audicon.db.entity.TrackEntity;
import audicon.db.entity.UserEntity;
import audicon.db.manager.base.BaseDBManager;

public class HistoryManager extends BaseDBManager {
	
	public HistoryManager() {
		super();
	}
	
	public List<TrackEntity> getTrackHistoryByUsername(final int user_id) {
		Session session = sessionFactory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TrackEntity> cr = cb.createQuery(TrackEntity.class);
		Root<TrackEntity> root = cr.from(TrackEntity.class);
		cr.select(root).where(cb.equal(root.get("user_id"), user_id));
		
		return session.createQuery(cr).getResultList();
	}

}
