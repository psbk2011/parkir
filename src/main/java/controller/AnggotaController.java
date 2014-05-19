package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Anggota;
import dao.AnggotaDao;
import dao.TransaksiDao;

@SuppressWarnings("serial")
@ManagedBean(name = "AnggotaController")
@SessionScoped
public class AnggotaController implements java.io.Serializable {
	private Anggota anggota;
	private List<AnggotaController> list;

	public AnggotaController() {
		if (anggota == null) {
			anggota = new Anggota();
		}
	}

	public Anggota getAnggota() {
		return anggota;
	}

	public void setAnggota(Anggota anggota) {
		this.anggota = anggota;
	}

	public List<AnggotaController> getList() {
		return list;
	}

	public void setList(List<AnggotaController> list) {
		this.list = list;
	}

	public void clearAnggota() {
		anggota.setBarcode(0);
		anggota.setIdAnggota("");
		anggota.setNamaLengkap("");
	}

	public void saveAnggota() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (anggota.getBarcode() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"Barcode tidak boleh 0"));
			} else if (anggota.getIdAnggota().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"id tidak boleh kosong"));
			} else if (anggota.getNamaLengkap().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erorr",
								"nama tidak boleh kosong"));
			} else {
				this.anggota.setBarcode(barcodeGen());

			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Failed"));
		}
		AnggotaDao dao = new AnggotaDao();
		dao.addAnggota(getAnggota());
		clearAnggota();
		context.addMessage(null, new FacesMessage("Sucess"));
	}

	public void deleteAnggota() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			AnggotaDao dao = new AnggotaDao();
			dao.deleteAnggota(getAnggota());
			context.addMessage(null, new FacesMessage("Sucess"));
			clearAnggota();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Failed"));
		}
	}

	public void updateAnggota() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			AnggotaDao dao = new AnggotaDao();
			dao.updateAnggota(getAnggota());
			context.addMessage(null, new FacesMessage("Sucess"));
			clearAnggota();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Failed"));
		}
	}

	public List<Anggota> getRead() {
		try {
			List<Anggota> anggota = new ArrayList<Anggota>();
			AnggotaDao dao = new AnggotaDao();
			anggota = dao.getAllAnggota();
			return anggota;

		} catch (NullPointerException e) {
			System.out.println("Erorr karena : " + e.getMessage());

		}
		return new ArrayList<Anggota>();

	}

	public int barcodeGen() {
		Random ran = new Random();
		int barcode = ran.nextInt(10);
		TransaksiDao c = new TransaksiDao();
		while (barcode != 11) {
			barcode += ran.nextInt(10);
		}

		return barcode;
	}

}
