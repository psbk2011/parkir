package dao;

import hibernate.HibernateUtils;
import model.Opsi;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class OpsiDao {
	public void addOpsi(Opsi opsi){
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(opsi);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		}
		session.close();
	}
	public void updateOpsi(Opsi opsi){
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(opsi);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		}
		session.close();
	}
	public void deleteOpsi(Opsi opsi){
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(opsi);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			}
		}
		session.close();
	}
}
