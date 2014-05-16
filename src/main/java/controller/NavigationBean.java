package controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520318172495977648L;

	public String toLogin() {
		return "/ViewLogin.jsf";
	}
	public String redirectToLogin(){
		return "/ViewLogin.jsf?faces-redirect=true";
	}
	public String viewOperator() {
		return "/resources/ViewOperator.jsf";
	}
	public String redirectToOperator(){
		return "/resources/ViewOperator.jsf?faces-redirect=true";
	}
	public String viewKendaraanKeluar(){
		return "/resources/ViewKendaraanKeluar.jsf";
	}
	public String redirectToKendaraanKeluar(){
		return "/resources/ViewKendaraanKeluar.jsf?faces-redirect=true";
	}
	public String viewKendaraanMasukMobil(){
		return "/resources/ViewKendaraanMasukMobil.jsf";
	}
	public String redirectKendaraanMasukMobil(){
		return "/ViewKendaraanMasukMobil.jsf";
	}
	public String viewKendaraanMasukMotor(){
		return "/resources/ViewKendaraanMasukMotor.jsf";
	}
	public String redirectKendaraanMasukMotor(){
		return "/ViewKendaraanMasukMotor.jsf";
	}
	public String viewLaporan(){
		return "/resources/ViewLaporan.jsf";
	}
	public String redirectLaporan(){
		return "/resources/ViewLaporan.jsf?faces-redirect=true";
	}
	public String viewOpsi(){
		return "/resources/ViewOpsi.jsf";
	}
	public String redirectOpsi(){
		return "/resources/ViewOpsi.jsf?faces-redirect=true";
	}

}
