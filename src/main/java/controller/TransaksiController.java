package controller;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	private String noPol;
	private int totBiaya;
	private int totBayar;
	private int cashBack;

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

	public String getNoPol() {
		return noPol;
	}

	public void setNoPol(String noPol) {
		this.noPol = noPol;
	}

	public int getTotBiaya() {
		return totBiaya;
	}

	public void setTotBiaya(int totBiaya) {
		this.totBiaya = totBiaya;
	}

	public int getTotBayar() {
		return totBayar;
	}

	public void setTotBayar(int totBayar) {
		this.totBayar = totBayar;
	}

	public int getCashBack() {
		return cashBack;
	}

	public void setCashBack(int cashBack) {
		this.cashBack = cashBack;
	}

	public void resetTransaksi() {
		transaksi.setBarcode("");
		transaksi.setNoPolisi("");
		transaksi.setTipeBayar("");
		transaksi.setTotalBiaya(0);

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
				getTransaksi().setWaktuKeluar(date);
				getTransaksi().setTipeBayar("Cash");
				this.transaksi.setNoPolisi(noPol);
				this.transaksi.setTotalBiaya(1000);
				this.cashBack = ((totBayar) - (1000));
				this.transaksi.setTotalPembayaran(cashBack);
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
