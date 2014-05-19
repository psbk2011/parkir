package dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import hibernate.HibernateUtils;
import model.Opsi;
import model.Transaksi;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class OpsiDao {
	public void addOpsi(Opsi opsi) {
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

	public void updateOpsi(Opsi opsi) {
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

	public void deleteOpsi(Opsi opsi) {
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

	public String getNilaiOpsi(Opsi opsi) {
		List<Opsi> list = new ArrayList<Opsi>();
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String sqlQuery = "SELECT nilai_opsi FROM opsi WHERE nama_opsi = '"
					+ opsi.getNamaOpsi() + "' LIMIT 1";
			list = session.createQuery(sqlQuery).list();
			return list.get(0).toString();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {

			}
			System.out.println("Erorr : " + e.getMessage());
		}
		return null;
	}

}
