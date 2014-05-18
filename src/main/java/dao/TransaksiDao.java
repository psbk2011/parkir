package dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hibernate.HibernateUtils;
import model.Transaksi;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.id.uuid.Helper;
import org.jboss.jandex.Main;

@ManagedBean
@SessionScoped
public class TransaksiDao implements Serializable {

	private int jumlahMotor = 100;
	

	public TransaksiDao() {

	}

	public int getJumlahMotor() {
		return jumlahMotor;
	}

	public void setJumlahMotor(int jumlahMotor) {
		this.jumlahMotor = jumlahMotor;
	}

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

	public void countMotor() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Transaksi.class);
		criteria.setProjection(Projections.rowCount());
		@SuppressWarnings("rawtypes")
		List list = criteria.list();
		Object count = list.get(0);
	
		System.out.println("jumlah :" + count);

	}

	public static void main(String[] args) {
		TransaksiDao o = new TransaksiDao();
		o.countMotor();
	}

}
