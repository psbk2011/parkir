package controller;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.LoginDao;
import model.Operator;

@ManagedBean(name = "LoginController")
@SessionScoped
public class LoginController {
	private Operator operator;
	private LoginDao daoLogin;
	private String menu;
	private boolean isLoggedIn;
	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	public LoginController() {
		daoLogin = new LoginDao(this);
		operator = new Operator();
		
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public void resetLogin() {
		operator.setNamaPengguna("");
		operator.setKataSandi("");
	}

	public String login() {
		String username = operator.getNamaPengguna();
		String password = operator.getKataSandi();
		Operator opr = new Operator(username, password);
		try {
			if (username.equals("")) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Isi username dulu"));
			} else if (password.equals("")) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Isi password dulu"));
			} else if (daoLogin.cekLogin(opr) && menu.equals("Pilih menu")) {
				isLoggedIn = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Pilih menu dulu"));
				return navigationBean.toLogin();
			} else if (daoLogin.cekLogin(opr)) {
				if (menu.equals("Operator")) {
					isLoggedIn = true;
					return navigationBean.redirectToOperator();
				} else if (menu.equals("Kendaraan Masuk Mobil")) {
					isLoggedIn = true;
					return navigationBean.redirectKendaraanMasukMobil();
				} else if (menu.equals("Kendaraan Masuk Motor")) {
					isLoggedIn = true;
					return navigationBean.redirectKendaraanMasukMotor();
				} else if (menu.equals("Kendaraan Keluar")) {
					isLoggedIn = true;
					return navigationBean.redirectToKendaraanKeluar();
				} else if (menu.equals("Opsi")) {
					isLoggedIn = true;
					return navigationBean.redirectOpsi();
				} else if (menu.equals("Anggota")) {
					isLoggedIn = true;
					return navigationBean.redirectToAnggota();
				} else if (menu.equals("Laporan")) {
					isLoggedIn = true;
					return navigationBean.redirectLaporan();
				} else {

				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Login gagal"));
			}
		} catch (Exception e) {
			System.out.println("Erorr :" + e.getMessage());
		}
		return navigationBean.toLogin();

	}

	public String doLogout() {
		isLoggedIn = false;
		return navigationBean.redirectToLogin();
	}

	public void doMasukMotor() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("ViewKendaraanMasukMotor.jsf");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void doMasukMobil() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("ViewKendaraanMasukMobil.jsf");
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
