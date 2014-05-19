package controller;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

import model.Transaksi;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.tools.Executable;

import dao.TransaksiDao;

@ManagedBean(name = "Transaksi")
@SessionScoped
@ViewScoped
public class TransaksiController implements Serializable {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Transaksi transaksi;
	private String barcode;
	private String gambarMasuk;
	private Date waktuKeluar;
	private String gambarKeluar;
	private Integer totalBiaya;
	private Integer totalPembayaran;
	private String noPolisi;
	private String tipeBayar;

	public TransaksiController() {
		transaksi = new Transaksi();
		countMobil();
		countMotor();
	}

	public Transaksi getTransaksi() {
		return transaksi;
	}

	public void setTransaksi(model.Transaksi transaksi) {
		this.transaksi = transaksi;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getGambarMasuk() {
		return gambarMasuk;
	}

	public void setGambarMasuk(String gambarMasuk) {
		this.gambarMasuk = gambarMasuk;
	}

	public Date getWaktuKeluar() {
		return waktuKeluar;
	}

	public void setWaktuKeluar(Date waktuKeluar) {
		this.waktuKeluar = waktuKeluar;
	}

	public String getGambarKeluar() {
		return gambarKeluar;
	}

	public void setGambarKeluar(String gambarKeluar) {
		this.gambarKeluar = gambarKeluar;
	}

	public Integer getTotalBiaya() {
		return totalBiaya;
	}

	public void setTotalBiaya(Integer totalBiaya) {
		this.totalBiaya = totalBiaya;
	}

	public Integer getTotalPembayaran() {
		return totalPembayaran;
	}

	public void setTotalPembayaran(Integer totalPembayaran) {
		this.totalPembayaran = totalPembayaran;
	}

	public String getNoPolisi() {
		return noPolisi;
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

	public void resetTransaksi() {
		setBarcode("");
		setNoPolisi("");
		setTipeBayar("");
		// setTotalBiaya(0);
	}

	public String barcodeGen() {
		Random ran = new Random();
		String barcode = Integer.toString(ran.nextInt(10));
		TransaksiDao c = new TransaksiDao();
		while (barcode.length() != 22) {
			barcode += Integer.toString(ran.nextInt(10));
		}

		return barcode;
	}

	public void printTiket(String pdfFilename) {
		try {
			Document doc = new Document(PageSize.A9.rotate());
			Executable ex = new Executable();
			String path = "e:/tmp/" + pdfFilename + ".pdf";
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(
					path));
			doc.setMargins(10, 1, 10, 1);
			doc.setMarginMirroring(true);

			doc.open();
			Font font = new Font(Font.HELVETICA, 9);
			doc.add(new Paragraph("Parkir Unpas IV", font));
			doc.add(new Paragraph(this.transaksi.getWaktuMasuk().toString()));
			PdfContentByte cb = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setCode(this.transaksi.getBarcode().toString());
			doc.add(code128.createImageWithBarcode(cb, null, null));
			doc.close();

			// Start Silent Print\

			PrinterJob job = PrinterJob.getPrinterJob();
			PageFormat pf = job.defaultPage();

			PDDocument document = PDDocument.load("e:/tmp/" + pdfFilename
					+ ".pdf");
			job.setPageable(new PDPageable(document, job));

			job.setJobName(pdfFilename + ".pdf");
			try {
				job.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}

		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void print_mobil() {
		try {
			Date date = new Date();
			sdf.format(date);

			this.transaksi.setWaktuMasuk(date);
			this.transaksi.setBarcode(barcodeGen());
			this.transaksi.setTipeKendaraan("Mobil");
			printTiket(this.getTransaksi().getBarcode().toString());
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

			this.transaksi.setWaktuMasuk(date);
			this.transaksi.setBarcode(barcodeGen());
			this.transaksi.setTipeKendaraan("Motor");

			printTiket(this.getTransaksi().getBarcode().toString());

		} catch (Exception e) {
			System.out.println("Error Print Tiket Mobil : " + e.getMessage());
		}
		TransaksiDao dao = new TransaksiDao();
		dao.addTransaksi(getTransaksi());
		resetTransaksi();
	}

	public void updateTransaksi() {
		TransaksiDao dao = new TransaksiDao();
		List<Transaksi> list = new ArrayList<Transaksi>();
		try {
			list = dao.cariBarcode(getTransaksi());
			if (list.size() > 0) {
				setTransaksi(list.get(0));
				Date date = new Date();
				sdf.format(date);

				setWaktuKeluar(date);
				getTransaksi().setWaktuKeluar(date);

				if (getTotalPembayaran() == -1) {
					setTipeBayar("Anggota");
					getTransaksi().setTipeBayar("Anggota");
					setTotalBiaya(0);
					getTransaksi().setTotalBiaya(0);
				} else if (getTotalPembayaran() == -2) {
					setTipeBayar("Bypass");
					getTransaksi().setTipeBayar("Bypass");
					setTotalBiaya(0);
					getTransaksi().setTotalBiaya(0);
				} else {
					setTipeBayar("Cash");
					getTransaksi().setTipeBayar("Cash");
					if (getTotalPembayaran() == 0) {
						if (getTransaksi().getTipeKendaraan().contains("Motor")) {
							setTotalBiaya(1000);
							getTransaksi().setTotalBiaya(1000);
						} else {
							setTotalBiaya(2000);
							getTransaksi().setTotalBiaya(2000);
						}
					} else {
						setTotalBiaya(totalPembayaran - getTotalBiaya());
						getTransaksi().setTotalBiaya(getTotalBiaya());
					}
				}

				setNoPolisi(noPolisi);
				getTransaksi().setNoPolisi(noPolisi);

				setTotalPembayaran(totalPembayaran);
				getTransaksi().setTotalPembayaran(totalPembayaran);
				dao.updateTransaksi(getTransaksi());

			} else {
				System.out.println("gagal Update");
			}

		} catch (Exception e) {
			System.out.println("Erorr Update karena : " + e.getMessage());
		}

	}

	public void countMotor() {
		try {
			TransaksiDao dao = new TransaksiDao();
			dao.countMotor(getTransaksi());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void countMobil() {
		try {
			TransaksiDao dao = new TransaksiDao();
			dao.countMobil(getTransaksi());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
