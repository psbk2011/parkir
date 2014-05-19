package dao;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import hibernate.HibernateUtils;
import model.Transaksi;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

	public boolean cariBarcode(Transaksi transaksi) {
		Transaction trns = null;
		boolean cek = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String sqlQuery = "FROM Transaksi WHERE barcode = '"
					+ transaksi.getBarcode() + "'";
			Query query = session.createQuery(sqlQuery);
			if (!query.list().isEmpty()) {
				cek = true;
				updateTransaksi(transaksi);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Barcode Ditemukan", transaksi.getBarcode()));
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
		return cek;

	}

	public void countMotor(Transaksi transaksi) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			String SQL_QUERY = "SELECT COUNT(*) FROM Transaksi WHERE tipe_kendaraan = 'Motor' and waktu_keluar is null";
			Query query = session.createQuery(SQL_QUERY);

			for (Iterator it = query.iterate(); it.hasNext();) {
				transaksi.setRowCount((Long) it.next());
				transaksi.setJumlahMotor((200) - transaksi.getRowCount());
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void countMobil(Transaksi transaksi) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			String SQL_QUERY = "SELECT COUNT(*) FROM Transaksi WHERE tipe_kendaraan = 'Mobil' and waktu_keluar is null";
			Query query = session.createQuery(SQL_QUERY);

			for (Iterator it = query.iterate(); it.hasNext();) {
				transaksi.setRowCount((Long) it.next());
				transaksi.setJumlahMobil((50) - transaksi.getRowCount());
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
