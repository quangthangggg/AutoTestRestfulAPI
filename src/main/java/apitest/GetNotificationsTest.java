package apitest;

import org.testng.Assert;

import apihelper.GetNotificationsHelper;
import io.restassured.response.Response;
import randomhelper.RandomString;

public class GetNotificationsTest {

	public void test1() {
		System.out.println("Test 1 in GetNotifications API: Input get a numeric value, code should be 1000 and message is OK:");
		
		String email = "auto@gmail.com";
		String password = "123456";
		
		RandomString rdStr = new RandomString();
		GetNotificationsHelper getNotif = new GetNotificationsHelper();
		String index, count, is_not_read;

		for(int i=0; i<5; i++) {
			try {			
				is_not_read = "";
				index = rdStr.getRandomNumericString(20);
				count = rdStr.getRandomNumericString(20);
				Response response = getNotif.getApiResponse(email, password, index, count, is_not_read);
				Assert.assertEquals(getNotif.getCodeResponse(response), 1000);
				Assert.assertEquals(getNotif.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 1: Passed");
			}
		}
		System.out.println("Test 1 finished");
	 }
	
	public void test2() {
		System.out.println("Test 2 in GetNotifications API: Input get a non-numeric value encountered (500 Internal Server Error)");
		
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetNotificationsHelper getNotif = new GetNotificationsHelper();
		String index, count, is_not_read = "";
		RandomString rdStr = new RandomString();

		for(int i=0; i<5; i++) {
			try {
				index = rdStr.getRandomString(20);
				count = rdStr.getRandomString(20);
				Response reponse = getNotif.getApiResponse(email, password, index, count, is_not_read);
				Assert.assertEquals(getNotif.getStatusCode(reponse), 500);
				System.out.println("Unit " + i + " in test 2: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 2: Failed");
			}
		}
		System.out.println("Test 2 finished");
	 }

	public void test3() {
		System.out.println("Test 3 in GetNotifications API: Input get null value, code should be 1000 and message is OK:");
		
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetNotificationsHelper getNotif = new GetNotificationsHelper();
		String index = "";
		String count = "";
		String is_not_read = "";
		
		//Unit 3
		try {			
			Response response = getNotif.getApiResponse(email, password, index, count, is_not_read);
			Assert.assertEquals(getNotif.getCodeResponse(response), 1000);
			Assert.assertEquals(getNotif.getMessageResponse(response), "OK");
	        System.out.println("Unit 3: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 3: Failed");
		}
		System.out.println("Test 3 finished");
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
