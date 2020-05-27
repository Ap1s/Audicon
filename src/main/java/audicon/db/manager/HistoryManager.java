package audicon.db.manager;

import java.util.List;
import audicon.functional.bo.*;

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
	
	public void saveHistoryEntry(final int user_id, final Track track) {
		TrackEntity trackEntity = new TrackEntity();
		trackEntity.setTitle(track.getTitle());
		trackEntity.setArtist(track.getArtist());
		trackEntity.setLength(track.getLength());
		trackEntity.setUser_id(user_id);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(trackEntity);
		session.getTransaction().commit();
		session.close();
	}

}
