package dao;

import java.util.ArrayList;
import java.util.List;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Operator;

public class OperatorDao {
	public void addOperator(Operator operator) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(operator);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {

			}
			e.printStackTrace();
		}
	}

	public void deleteOperator(Operator opeartor) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(opeartor);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {

			}
			e.printStackTrace();
		}
	}

	public void updateOperator(Operator opeartor) {
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(opeartor);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			if (trns != null) {
				trns.rollback();
			} else {

			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Operator> getAllOperator() {
		List<Operator> operator = new ArrayList<Operator>();
		Transaction trns = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			operator = session.createQuery("from Operator").list();
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
		return operator;
	}

}
