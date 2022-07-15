package apitest;

import org.testng.Assert;

import apihelper.*;
import io.restassured.response.Response;

public class LogoutTest {

	public void test1() {
		System.out.println("Test 1 in Logout API: The code and message should be 1000 and \"OK\" respectively when login correctly");
		LogoutHelper logout = new LogoutHelper();
		String email = "auto@gmail.com";
		String password = "123456";
		try {
			Response response = logout.getApiResponse(email, password);
			Assert.assertEquals(logout.getCodeResponse(response), 1000);
			Assert.assertEquals(logout.getMessageResponse(response), "OK");
	        System.out.println("Unit 1 in test 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1 in test 1: Failed");
		} finally {
			System.out.println("Test 1 finished!");
		}
	}
	
	public void test2() {
		System.out.println("Test 2 in LogOut API: The code and message ");
		LogoutHelper logout = new LogoutHelper();
		String email = "a@gmail.com";
		String password = "123456p";
		try {
			Response response = logout.getApiResponse(email, password);
			Assert.assertEquals(logout.getCodeResponse(response), 1000);
			Assert.assertEquals(logout.getMessageResponse(response), "OK");
	        System.out.println("Unit 1 in test 2: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1 in test 2: Failed");
		} finally {
			System.out.println("Test 2 finished");
		}
	}
	
	public void chooseTest(String select) {
		switch(select) {
		case "0": 
			this.test1();
			this.test2();
			break;
		case "1": 
			this.test1();
			break;
		case "2":
			this.test2();
			break;
		default:
			break;
		}
	}
}
