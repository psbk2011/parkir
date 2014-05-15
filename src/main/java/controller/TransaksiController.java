package controller;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

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
			String path = "d:/tmp/" + pdfFilename + ".pdf";
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

			PDDocument document = PDDocument.load("d:/tmp/" + pdfFilename
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

	public static void main(String[] args) {
		TransaksiController tc = new TransaksiController();
		tc.printTiket("test.pdf");
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
				// dao.updateTransaksi(getTransaksi());
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
