package controller;

import java.util.ArrayList;
import java.util.List;

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

	public OperatorController() {
		operator = new Operator();
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
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

	public List<Operator> getAllOperator() {
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

}
