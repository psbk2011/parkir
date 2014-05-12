package controller;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import dao.TransaksiDao;

@ManagedBean(name = "Transaksi")
@SessionScoped
public class TransaksiController implements Serializable {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = 1L;
	private model.Transaksi transaksi;

	public TransaksiController() {
		transaksi = new model.Transaksi();
	}

	public model.Transaksi getTransaksi() {
		return transaksi;
	}

	public void setTransaksi(model.Transaksi transaksi) {
		this.transaksi = transaksi;
	}

	public void resetTransaksi() {
		transaksi.setBarcode("");
		transaksi.setNoPolisi("");
		transaksi.setTipeBayar("");
		transaksi.setTotalBiaya(0);

	}

	public void print_mobil() {
		try {
			Date date = new Date();
			sdf.format(date);

			Barcode128 code = new Barcode128();
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			String randomNum = new Integer(prng.nextInt()).toString();

			this.transaksi.setWaktuMasuk(date);
			this.transaksi.setBarcode(randomNum);
			this.transaksi.setTipeKendaraan("Mobil");

			code.setCode(randomNum);
			code.setBarHeight(80f);
			code.setX(1f);
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(
							"C:/JSF/Parkir-Master/src/main/webapp/image/barcode/"
									+ randomNum + ".pdf"));
			document.setPageSize(PageSize.A5);
			document.setMargins(20, 20, 20, 20);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			Image barcodeimage = code.createImageWithBarcode(cb, Color.BLACK,
					Color.BLACK);
			document.add(barcodeimage);
			document.close();
		} catch (Exception e) {
			System.out.println("Error Print Tiket Mobil : " + e.getMessage());
		}
		TransaksiDao dao = new TransaksiDao();
		dao.addTransaksi(getTransaksi());
		resetTransaksi();
	}

	public void print_motor() {
		try {
			Date date = new Date();
			sdf.format(date);

			Barcode128 code = new Barcode128();
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			String randomNum = new Integer(prng.nextInt()).toString();

			this.transaksi.setWaktuMasuk(date);
			this.transaksi.setBarcode(randomNum);
			this.transaksi.setTipeKendaraan("Motor");

			// code.setCode(randomNum);
			// code.setBarHeight(80f);
			// code.setX(1f);
			// Document document = new Document();
			// PdfWriter writer = PdfWriter.getInstance(document,
			// new FileOutputStream(
			// "C:/JSF/Parkir-Master/src/main/webapp/image/barcode/"
			// + randomNum + ".pdf"));
			// document.setPageSize(PageSize.A5);
			// document.setMargins(20, 20, 20, 20);
			// document.open();
			// PdfContentByte cb = writer.getDirectContent();
			// Image barcodeimage = code.createImageWithBarcode(cb, Color.BLACK,
			// Color.BLACK);
			// document.add(barcodeimage);
			// document.close();

		} catch (Exception e) {
			System.out.println("Error Print Tiket Mobil : " + e.getMessage());
		}
		TransaksiDao dao = new TransaksiDao();
		dao.addTransaksi(getTransaksi());
		resetTransaksi();
	}

	public void cariBarcode(ActionEvent actionEvent) {
		try {
			if (transaksi.getBarcode().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Barcode Tidak boleh Kosong"));
			} else if (transaksi.getNoPolisi().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"No Polisi Tidak boleh Kosong"));
			} else {
				TransaksiDao dao = new TransaksiDao();
				dao.cariBarcode(getTransaksi());
				resetTransaksi();
//				dao.updateTransaksi(getTransaksi());
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
							"Failed"));
		}
	}

	public void updateTransaksi() {
		try {
			Date date = new Date();
			sdf.format(date);
			this.transaksi.setWaktuKeluar(date);
			this.transaksi.setTotalPembayaran(1000);

		} catch (Exception e) {
			System.out.println("Erorr Update karena : " + e.getMessage());
		}
		TransaksiDao dao = new TransaksiDao();
		dao.updateTransaksi(getTransaksi());
	}

}
