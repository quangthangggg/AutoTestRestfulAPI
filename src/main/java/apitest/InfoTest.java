package apitest;

import org.testng.Assert;

import apihelper.InfoHelper;
import io.restassured.response.Response;

public class InfoTest {
	
	public void test1() {
		System.out.println("Test 1 in Info Api: Code and Message should be 1000 and \"OK\" when login correctly");
		InfoHelper info = new InfoHelper();
		String email, password;
		email = "auto@gmail.com";
		password = "123456";
		try {
			Response response = info.getApiResponse(email, password);
			Assert.assertEquals(info.getCodeResponse(response), 1000);
			Assert.assertEquals(info.getMessageResponse(response), "OK");
			System.out.println("Test 1: Passed");
		} catch(AssertionError e) {
			System.out.println("Test 1: Failed");
		}
		System.out.println("Test 1 Finished");
	}
	
	public void test2() {
		System.out.println("Test 2 in Info Api: Code and Message should be 1004 when login incorrectly");
		InfoHelper info = new InfoHelper();
		String email, password;
		email = "-";
		password = "-";
		try {
			Response response = info.getApiResponse(email, password);
			Assert.assertEquals(info.getStatusCode(response), 200);
			Assert.assertEquals(info.getCodeResponse(response), 1004);
			System.out.println("Test 2: Passed");
		} catch(AssertionError e) {
			System.out.println("Test 2: Failed");
		}
		System.out.println("Test 2 Finished");
	}
	
	public void test3() {
		System.out.println("Test 2 in Info Api: Code and Message should be 1004 when not login");
		InfoHelper info = new InfoHelper();

		try {
			Response response = info.getApiResponse();
			Assert.assertEquals(info.getStatusCode(response), 200);
			Assert.assertEquals(info.getCodeResponse(response), 1004);
			System.out.println("Test 3: Passed");
		} catch(AssertionError e) {
			System.out.println("Test 3: Failed");
		}
		System.out.println("Test 3 Finished");
	}
		
		public void chooseTest(String select) {
			switch(select) {
			case "0": 
				this.test1();
				this.test2();
				this.test3();
				break;
			case "1": 
				this.test1();
				break;
			case "2":
				this.test2();
				break;
			case "3":
				this.test3();
				break;
			default:
				break;
			}
		}
}
