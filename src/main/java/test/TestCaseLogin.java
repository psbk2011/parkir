package test;

import static org.junit.Assert.*;
import model.Operator;

import org.junit.*;

import controller.LoginController;

public class TestCaseLogin {

	@Test
	public void test() {
		LoginController controller = new LoginController();
		Operator operator = new Operator();
		operator.setNamaPengguna("Achmad");
		operator.setKataSandi("123qweasd");
		controller.setOperator(operator);
		controller.setMenu("Operator");
		assertNotEquals("/ViewLogin.jsf", controller.login());
	}
	
	@Test
	public void testLogOut() {
		LoginController controller = new LoginController();
		assertEquals("/ViewLogin.jsf?faces-redirect=true", controller.doLogout());
	}

}
