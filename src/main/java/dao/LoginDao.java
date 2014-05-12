package dao;

import hibernate.HibernateUtils;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Operator;

public class LoginDao {
	private Session session;

	public boolean cekLogin(Operator operator) {
		boolean kondisi = false;
		session = HibernateUtils.getSessionFactory().openSession();
		try {
			String sqlQuery = "FROM Operator WHERE nama_pengguna = '"
					+ operator.getNamaPengguna() + "' and kata_sandi = '"
					+ operator.getKataSandi() + "'";
			Query query = session.createQuery(sqlQuery);
			if (!query.list().isEmpty()) {
				kondisi = true;
			} else {
			}
		} catch (Exception e) {
		}
		return kondisi;
	}

}
