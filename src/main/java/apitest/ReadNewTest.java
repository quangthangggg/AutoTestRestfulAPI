package apitest;

import java.util.ArrayList;

import org.testng.Assert;

import apihelper.GetNewsHelper;
import apihelper.ReadNewHelper;
import io.restassured.response.Response;
import randomhelper.RandomInteger;

public class ReadNewTest {

	public void test1() {
		System.out.println("Test 1 in ReadNews API: The code should be 1000 and message is OK when passing correctly");
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetNewsHelper getNews = new GetNewsHelper();
		
		String new_id;
		ReadNewHelper readNew = new ReadNewHelper();
		
		RandomInteger rdInt = new RandomInteger();
		ArrayList<String> listNewId = getNews.getListNewId();
		for(int i=0; i<5; i++) {
			new_id = "/" + listNewId.get(rdInt.getRandomInteger(0, listNewId.size()-1));
			try {			
				Response response = readNew.getApiResponse(email, password, new_id);		
				Assert.assertEquals(readNew.getCodeResponse(response), 1000);
				Assert.assertEquals(readNew.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 1: Failed");
			} 
		}
			
		System.out.println("Test 1 finished");

	 }
	
	public void test2() {
		System.out.println("Test 2 in ReadNews API: When assing new_id that does not exist, (404 NOT FOUND)");
		String email = "auto@gmail.com";
		String password = "123456";
		
		ReadNewHelper readNew = new ReadNewHelper();
		String new_id;
		
		RandomInteger rdInt = new RandomInteger();
		for(int i=0; i<5; i++) {
			try {
				new_id = "/" + Integer.toString(rdInt.getRandomInteger(0, 100));
				Response reponse = readNew.getApiResponse(email, password, new_id);
				Assert.assertEquals(readNew.getStatusCode(reponse), 404);
				System.out.println("Unit " + i + " in test 2: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 2: Failed");
			}
		}
		System.out.println("Test 2 finished");
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
