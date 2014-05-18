package controller;

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
		operator = new Operator();
		daoLogin = new LoginDao();
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
				}else if(menu.equals("Anggota")){
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
}
