package test;

import static org.junit.Assert.*;
import model.Transaksi;

import org.junit.*;

import controller.TransaksiController;

public class TestCaseTransaksi {

	private controller.TransaksiController controller;
	private model.Transaksi transaksi;
	
	@Test
	public void test() {
		controller = new controller.TransaksiController();
		controller.print_motor();
		String barcode = controller.getTransaksi().getBarcode();
		transaksi = new Transaksi();
		transaksi.setBarcode(barcode);
		transaksi.setNoPolisi("113040045");
		controller.setTransaksi(transaksi);
		controller.updateTransaksi();
	}
	
	@Test
	public void testBarcodeGenerator() {
		TransaksiController controller = new TransaksiController();
		//Setahun = 366 hari dan sehari = 2000 tiket
		int hari = 366;
		int tiketPerHari = 2000;
		int jumlahBarcode = hari * tiketPerHari;
		System.out.println(jumlahBarcode+" tiket");
		String[] barcode = new String[jumlahBarcode];
		for (int i = 0; i < barcode.length; i++) {
			barcode[i] = controller.barcodeGen();
		}
		
		for (int i = 0; i < barcode.length; i++) {
			System.out.print("Tiket ke - "+i+" : "+barcode[i]);
			for (int j = 0; j < barcode.length; j++) {
				if(i != j){
					assertNotEquals(barcode[i], barcode[j]);
				}
			}
		}
	}
}
