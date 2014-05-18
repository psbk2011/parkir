package dao;

import hibernate.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

import model.Anggota;
import model.Operator;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AnggotaDao {

	public void addAnggota(Anggota anggota) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(anggota);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {

			}
			e.printStackTrace();
		}
	}

	public void deleteAnggota(Anggota anggota) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(anggota);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {
				e.printStackTrace();
			}
		}

	}

	public void updateAnggota(Anggota anggota) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(anggota);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Anggota> getAllAnggota() {
		List<Anggota> anggota = new ArrayList<Anggota>();
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			anggota = session.createQuery("from Anggota").list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return anggota;
	}

}
