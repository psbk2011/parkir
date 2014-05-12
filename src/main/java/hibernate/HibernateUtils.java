package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static final SessionFactory sessionFactory = buildSessionfactory();
			
	public static SessionFactory buildSessionfactory(){
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
		}
	
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
