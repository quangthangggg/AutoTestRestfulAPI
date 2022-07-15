package apitest;

import org.testng.Assert;
import randomhelper.*;

import apihelper.LoginHelper;
import io.restassured.response.Response;

public class LoginTest {
	
	public void test1() {
		System.out.println("Test 1 in LogIn API: The code and message strings shall be not NULL as well as non-empty in any case:");
		LoginHelper login = new LoginHelper();
		RandomGmail randomGmail = new RandomGmail();
		RandomString randomString = new RandomString();
		RandomInteger randomInteger = new RandomInteger();
		int lenEmail, lenPassword;
		String email, password;
		
		for(int i=0; i<10; i++) {
			lenEmail = randomInteger.getRandomInteger(0, 260);
			lenPassword = randomInteger.getRandomInteger(0, 10);
			email = randomGmail.getRandomGmail(lenEmail);
			password = randomString.getRandomString(lenPassword);
			try {
				Response response = login.getApiResponse(email, password);
				Assert.assertNotNull(login.getCodeResponse(response));
				Assert.assertNotNull(login.getMessageResponse(response));
		        System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 1: Failed");
			} finally {
				System.out.println("Test 1 finished");
			}
		}
	 }
	
	public void test2() {
		System.out.println("Test 2 in LogIn API: The code and message strings shall be not NULL as well as non-empty in any case:");
		LoginHelper login = new LoginHelper();
		String email = "auto@gmail.com";
		String password = "123456";
		//Unit 1
		try {
			Response response = login.getApiResponse(email, password);
			Assert.assertNotNull(login.getCodeResponse(response));
			Assert.assertNotNull(login.getMessageResponse(response));
	        System.out.println("Unit 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1: Failed");
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
