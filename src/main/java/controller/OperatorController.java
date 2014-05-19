package controller;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Operator;
import dao.OperatorDao;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class OperatorController implements java.io.Serializable {
	private Operator operator;
	private List<OperatorController> list;

	public OperatorController() {
		if (operator == null) {
			operator = new Operator();
		}
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<OperatorController> getList() {
		return list;
	}

	public void setList(List<OperatorController> list) {
		this.list = list;
	}

	public void saveOperator(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (operator.getNamaPengguna().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Nama Pengguna Tidak boleh Kosong"));
			} else if (operator.getNamaLengkap().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Nama Lengkap Tidak boleh Kosong"));
			} else if (operator.getKataSandi().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Kata Sandi Tidak boleh Kosong"));
			} else {
				this.operator.setKataSandi(getMD5(operator.getKataSandi()));
				OperatorDao dao = new OperatorDao();
				dao.addOperator(getOperator());
				clearOperator();
				context.addMessage(null, new FacesMessage("Sucess"));
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Failed"));
		}
	}

	public void clearOperator() {
		operator.setNamaLengkap("");
		operator.setNamaPengguna("");
		operator.setKataSandi("");
	}

	public void deleteOperator(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			OperatorDao dao = new OperatorDao();
			dao.deleteOperator(getOperator());
			context.addMessage(null, new FacesMessage("Sucess"));
			clearOperator();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Failed"));
		}
	}

	public void updateOperator(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			OperatorDao dao = new OperatorDao();
			dao.updateOperator(getOperator());
			context.addMessage(null, new FacesMessage("Sucess"));
			clearOperator();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Failed"));
		}
	}

	public List<Operator> getRead() {
		try {
			List<Operator> operator = new ArrayList<Operator>();
			OperatorDao dao = new OperatorDao();
			operator = dao.getAllOperator();
			return operator;

		} catch (NullPointerException e) {
			System.out.println("Erorr karena : " + e.getMessage());

		}
		return new ArrayList<Operator>();

	}

	public void logoutOperator() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("ViewLogin.jsf");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getMD5(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encrip cannot be null");

		}
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (Exception ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0").append(
						Integer.toHexString(0xFF & hash[i]));

			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}

}
