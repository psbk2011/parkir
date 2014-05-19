package test;

import static org.junit.Assert.*;
import model.Operator;

import org.junit.*;

import controller.OperatorController;

public class TestCaseOperator {
	private controller.OperatorController controller;
	private model.Operator operator;
	
	@Test
	public void test() {
		controller = new controller.OperatorController();
		operator = new Operator();
		operator.setNamaLengkap("Achmad Fuady");
		operator.setNamaPengguna("Achmad");
		operator.setKataSandi("123qweasd");
		controller.setOperator(operator);
		controller.saveOperator(null);
		operator = new Operator();
		operator.setNamaLengkap("Test Update");
		operator.setNamaPengguna("Achmad");
		operator.setKataSandi("123qweasd");
		controller.setOperator(operator);
		controller.saveOperator(null);
		operator = controller.getOperator();
		operator.setAlamat("Jalan Margasari no.201");
		operator.setNomorTelepon("6285759130126");
		controller.setOperator(operator);
		controller.updateOperator(null);
		operator = new Operator();
		operator.setNamaLengkap("Test Delete");
		operator.setNamaPengguna("Achmad");
		operator.setKataSandi("123qweasd");
		controller.setOperator(operator);
		controller.saveOperator(null);
		controller.deleteOperator(null);
	}
	
	@Test
	public void testGetAllOperator(){
		controller = new OperatorController();
		assertNotEquals(null, controller.getAllOperator());
	}
}
