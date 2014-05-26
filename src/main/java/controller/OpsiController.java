package controller;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dao.OperatorDao;
import dao.OpsiDao;
import dao.TransaksiDao;
import model.Opsi;

@ManagedBean(name = "Opsi")
@SessionScoped
public class OpsiController {
	OpsiDao dao = new OpsiDao();
	Opsi opsi = new Opsi();

	
	public String get_hargamotor() {
		return search("harga_motor");
	}
	
	public String get_hargamobil() {
		return search("harga_mobil");
	}
	
	public String get_kapasitasmotor() {
		return search("kapasitas_motor");
	}
	
	public String get_kapasitasmobil() {
		return search("kapasitas_mobil");
	}
	
	public String get_pathtmp() {
		return search("path_tmp");
	}
	
	
	public void set_hargamobil() {
		updateOpsi(opsi);
	}
	
	public String search(String data) {
		try {
			opsi.setNamaOpsi(data);
			return dao.getNilaiOpsi(opsi);
		} catch (Exception e) {
			System.out.println("Error Search : " + e.getMessage());
			return null;
		}
	}
	
	public void saveOpsi() {
		try {
//			dao.addOpsi(getOpsi());
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
		}
	}

	public void deleteOpsi() {
		try {
//			dao.deleteOpsi(getOpsi());
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
		}
	}

	public void updateOpsi(Opsi opsi) {
		try {
			dao.updateOpsiWhere(opsi);
		} catch (Exception e) {
			System.out.println("failed" + e.getMessage());
		}
	}

}
