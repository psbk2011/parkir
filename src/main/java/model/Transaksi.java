package model;

// Generated Apr 15, 2014 12:50:33 AM by Hibernate Tools 3.4.0.CR1

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.TransaksiDao;

/**
 * Transaksi generated by hbm2java
 */
public class Transaksi implements java.io.Serializable {
	private int idtransaksi;
	private String barcode;
	private String tipeKendaraan;
	private Date waktuMasuk;
	private String gambarMasuk;
	private Date waktuKeluar;
	private String gambarKeluar;
	private Integer totalBiaya;
	private Integer totalPembayaran;
	private String noPolisi;
	private String tipeBayar;
	StringBuilder result = new StringBuilder();
	private Set transaksiAnggotas = new HashSet(0);
	private int transaksiSelected;
	private long rowCount;
	private long jumlahMotor;
	private long jumlahMobil;


	public Transaksi() {
	}

	public Transaksi(int idtransaksi, String barcode, String tipeKendaraan,
			Date waktuMasuk, String gambarMasuk, Date waktuKeluar,
			String gambarKeluar, Integer totalBiaya, Integer totalPembayaran,
			String noPolisi,String tipeBayar) {
		this.idtransaksi = idtransaksi;
		this.barcode = barcode;
		this.tipeKendaraan = tipeKendaraan;
		this.waktuMasuk = waktuMasuk;
		this.gambarMasuk = gambarMasuk;
		this.waktuKeluar = waktuKeluar;
		this.gambarKeluar = gambarKeluar;
		this.totalBiaya = totalBiaya;
		this.totalPembayaran = totalPembayaran;
		this.noPolisi = noPolisi;
	}

	public int getIdtransaksi() {
		return this.idtransaksi;
	}

	public void setIdtransaksi(int idtransaksi) {
		this.idtransaksi = idtransaksi;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTipeKendaraan() {
		return tipeKendaraan;
	}

	public void setTipeKendaraan(String tipeKendaraan) {
		this.tipeKendaraan = tipeKendaraan;
	}

	public Date getWaktuMasuk() {
		return this.waktuMasuk;
	}

	public void setWaktuMasuk(Date waktuMasuk) {
		this.waktuMasuk = waktuMasuk;
	}

	public String getGambarMasuk() {
		return this.gambarMasuk;
	}

	public void setGambarMasuk(String gambarMasuk) {
		this.gambarMasuk = gambarMasuk;
	}

	public Date getWaktuKeluar() {
		return this.waktuKeluar;
	}

	public void setWaktuKeluar(Date waktuKeluar) {
		this.waktuKeluar = waktuKeluar;
	}

	public String getGambarKeluar() {
		return this.gambarKeluar;
	}

	public void setGambarKeluar(String gambarKeluar) {
		this.gambarKeluar = gambarKeluar;
	}

	public Integer getTotalBiaya() {
		return this.totalBiaya;
	}

	public void setTotalBiaya(Integer totalBiaya) {
		this.totalBiaya = totalBiaya;
	}

	public Integer getTotalPembayaran() {
		return this.totalPembayaran;
	}

	public void setTotalPembayaran(Integer totalPembayaran) {
		this.totalPembayaran = totalPembayaran;
	}

	public String getNoPolisi() {
		return this.noPolisi;
	}

	public void setNoPolisi(String noPolisi) {
		this.noPolisi = noPolisi;
	}
	
	public String getTipeBayar() {
		return tipeBayar;
	}

	public void setTipeBayar(String tipeBayar) {
		this.tipeBayar = tipeBayar;
	}

	public int getTransaksiSelected() {
		return transaksiSelected;
	}

	public void setTransaksiSelected(int transaksiSelected) {
		this.transaksiSelected = transaksiSelected;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public long getJumlahMotor() {
		return jumlahMotor;
	}

	public void setJumlahMotor(long jumlahMotor) {
		this.jumlahMotor = jumlahMotor;
	}

	public long getJumlahMobil() {
		return jumlahMobil;
	}

	public void setJumlahMobil(long jumlahMobil) {
		this.jumlahMobil = jumlahMobil;
	}
	
}
