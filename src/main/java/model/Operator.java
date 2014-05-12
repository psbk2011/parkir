package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Generated May 2, 2014 10:14:12 AM by Hibernate Tools 3.4.0.CR1

/**
 * Operator generated by hbm2java
 */
@Entity
@Table(name = "operator")
@ManagedBean
@SessionScoped
public class Operator implements java.io.Serializable {

	private int idoperator;
	private String namaLengkap;
	private String namaPengguna;
	private String kataSandi;
	private String alamat;
	private String nomorTelepon;
	private Operator selectedOperator;

	public Operator() {
	}

	public Operator(String namaLengkap, String namaPengguna, String kataSandi) {
		this.namaLengkap = namaLengkap;
		this.namaPengguna = namaPengguna;
		this.kataSandi = kataSandi;
	}
	public Operator(String namaPengguna, String kataSandi){
		this.namaPengguna = namaPengguna;
		this.kataSandi = kataSandi;
	}

	public Operator(String namaLengkap, String namaPengguna, String kataSandi,
			String alamat, String nomorTelepon) {
		this.namaLengkap = namaLengkap;
		this.namaPengguna = namaPengguna;
		this.kataSandi = kataSandi;
		this.alamat = alamat;
		this.nomorTelepon = nomorTelepon;
	}

	@Id
	@Column (name = "idoperator", unique = true, nullable = false)
	public int getIdoperator() {
		return this.idoperator;
	}

	public void setIdoperator(int idoperator) {
		this.idoperator = idoperator;
	}

	public String getNamaLengkap() {
		return this.namaLengkap;
	}

	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}

	public String getNamaPengguna() {
		return this.namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	public String getKataSandi() {
		return this.kataSandi;
	}

	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}

	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getNomorTelepon() {
		return this.nomorTelepon;
	}

	public void setNomorTelepon(String nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}

	public Operator getSelectedOperator() {
		return selectedOperator;
	}

	public void setSelectedOperator(Operator selectedOperator) {
		this.selectedOperator = selectedOperator;
	}

}
