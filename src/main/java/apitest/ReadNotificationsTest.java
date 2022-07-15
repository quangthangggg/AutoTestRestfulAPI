package apitest;

import java.util.ArrayList;

import org.testng.Assert;

import apihelper.*;
import io.restassured.response.Response;
import randomhelper.*;

public class ReadNotificationsTest {
	
	public void test1() {
		System.out.println("Test 1 in ReadNotifications API: input auction_deny_id get numeric random value in auction id");
		
		String email = "auto@gmail.com";
		String password = "123456";
		
		ReadNotificationsHelper readNotif = new ReadNotificationsHelper();
		
		RandomInteger rdInt = new RandomInteger();
		GetListAuctionsHelper listAuction = new GetListAuctionsHelper();
		ArrayList<String> listId = listAuction.getListAuctionId();
		
		for(int i=0; i<5; i++) {
			String auction_deny_id = "/" + listId.get(rdInt.getRandomInteger(0, listId.size()-1));
			try {					
				Response response = readNotif.getApiResponse(email, password, auction_deny_id);
				Assert.assertEquals(readNotif.getCodeResponse(response), 1000);
				Assert.assertEquals(readNotif.getMessageResponse(response), "OK");
		        System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
		        System.out.println("Unit " + i + " in test 1: Failed");
			}
		}
		System.out.println("Test 1 finished");
			
	 }
	
	public void test2() {
		System.out.println("Test 2 in ReadNotifications API: input new_id get non-numeric value");
		
		String email = "auto@gmail.com";
		String password = "123456";
		
		ReadNotificationsHelper readNotif = new ReadNotificationsHelper();
		
		String auction_deny_id;
		RandomString rdStr = new RandomString();
		RandomInteger rdInt = new RandomInteger();
		
		for(int i=0; i<5; i++) {
			try {
				auction_deny_id = "/" + rdStr.getRandomString(rdInt.getRandomInteger(0, 20));
				Response reponse = readNotif.getApiResponse(email, password, auction_deny_id);
				Assert.assertEquals(readNotif.getStatusCode(reponse), 500);
				System.out.println("Unit " + i + " in test 2: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 2: Failed");
			}
		}
		System.out.println("Test 2 finished");
	}
	
	public void test3() {
		System.out.println("Test 3 in ReadNotifications API: input new_id get null value");
		
		String email = "auto@gmail.com";
		String password = "123456";
		
		ReadNotificationsHelper readNotif = new ReadNotificationsHelper();
		String auction_deny_id = "";
		try {			
			Response response = readNotif.getApiResponse(email, password, auction_deny_id);
			Assert.assertEquals(response.getStatusCode(), 404);
			System.out.println("Unit 1 in test 3: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1 in test 3: Failed");
		}
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
