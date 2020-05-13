package audicon.db.manager.base;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class BaseDBManager {
	protected SessionFactory sessionFactory;
	
	public BaseDBManager() {
		setup();
	}
	
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new Configuration().configure().buildSessionFactory();

		} catch (Exception ex) {
		    ex.printStackTrace();
		    StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
