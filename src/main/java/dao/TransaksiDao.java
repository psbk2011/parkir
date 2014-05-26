package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import hibernate.HibernateUtils;
import model.Transaksi;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.OpsiController;

public class TransaksiDao {
	public void addTransaksi(Transaksi transaksi) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(transaksi);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {
			}
			e.printStackTrace();
		}
	}

	public void deleteTransaksi(Transaksi transaksi) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(transaksi);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {
				System.out.println("Erorr Handling");

			}
		}
	}

	public void updateTransaksi(Transaksi transaksi) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(transaksi);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {
				System.out.println("Erorr Handling");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Transaksi> cariBarcode(Transaksi transaksi) {
		List<Transaksi> list = new ArrayList<Transaksi>();
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String barcode = transaksi.getBarcode().substring(0, 22);
			String sqlQuery = "FROM Transaksi WHERE barcode = '"
					+ barcode + "'";
			list = session.createQuery(sqlQuery).list();
			if (list.size() > 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Barcode Ditemukan", transaksi.getBarcode()));
				return list;
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Barcode Tidak Ditemukan"));
			}

		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {

			}
			System.out.println("Erorr : " + e.getMessage());
		}
		return list;

	}

	public String countMotor(Transaksi transaksi) {
		try {
			OpsiController oc = new OpsiController();
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query query = session.createSQLQuery("SELECT COUNT(*) FROM Transaksi WHERE tipe_kendaraan = 'Motor' AND waktu_keluar IS NULL");
			List result = query.list();
			int count = Integer.parseInt(oc.get_kapasitasmotor()) - Integer.parseInt(result.get(0).toString());
			return Integer.toString(count);
		} catch (Exception e) {
			System.out.println("Error DAO Count Motor : " + e.getMessage());
		}
		return null;
	}
	
	public String countMobil(Transaksi transaksi) {
		try {
			OpsiController oc = new OpsiController();
			Session session = HibernateUtils.getSessionFactory().openSession();
			Query query = session.createSQLQuery("SELECT COUNT(*) FROM Transaksi WHERE tipe_kendaraan = 'Mobil' AND waktu_keluar IS NULL");
			List result = query.list();
			int count = Integer.parseInt(oc.get_kapasitasmobil()) - Integer.parseInt(result.get(0).toString());
			return Integer.toString(count);
		} catch (Exception e) {
			System.out.println("Error DAO Count Motor : " + e.getMessage());
		}
		return null;
	}
}
