package dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import hibernate.HibernateUtils;
import model.Opsi;
import model.Transaksi;

import org.hibernate.Query;
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
	
	public void updateOpsiWhere(Opsi opsi) {
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query query = session.createSQLQuery("UPDATE Opsi SET nilai_opsi='"+opsi.getNilaiOpsi()+"' WHERE nama_opsi = '"
					+ opsi.getNamaOpsi() + "'");
		} catch (Exception e) {
			System.out.println("Error Opsi Where : "  + e.getMessage());
		}
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
		try {
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query query = session.createSQLQuery("SELECT nilai_opsi FROM Opsi WHERE nama_opsi = '"
					+ opsi.getNamaOpsi() + "'");
			List result = query.list();
			return result.get(0).toString();
		} catch (Exception e) {
			System.out.println("Error DAO Cari : " + e.getMessage());
		}
		return null;
	}

}
