package controller;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.OperatorDao;
import dao.OpsiDao;
import dao.TransaksiDao;
import model.Opsi;

@ManagedBean(name = "Opsi")
@SessionScoped
public class OpsiController {
	private Opsi opsi;

	public OpsiController() {
		opsi = new Opsi();
	}

	public Opsi getOpsi() {
		return opsi;
	}

	public void setOpsi(Opsi opsi) {
		this.opsi = opsi;
	}
	
//	public int getValueOpsi() {
//		OpsiDao od = new OpsiDao();
//		od.getNilaiOpsi(opsi);
//		return 0;
//	}

	public void saveOpsi() {
		try {
			OpsiDao dao = new OpsiDao();
			dao.addOpsi(getOpsi());
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
		}
	}

	public void deleteOpsi() {
		try {
			OpsiDao dao = new OpsiDao();
			dao.deleteOpsi(getOpsi());
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
		}
	}

	public void updateOpsi() {
		try {
			OpsiDao dao = new OpsiDao();
			dao.updateOpsi(getOpsi());
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
		}
	}

}
