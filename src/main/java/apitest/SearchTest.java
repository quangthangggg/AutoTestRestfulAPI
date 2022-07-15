package apitest;

import org.testng.Assert;

import apihelper.SearchHelper;
import io.restassured.response.Response;
import randomhelper.*;

public class SearchTest {

	public void test1() {
		System.out.println("Test 1 in Search API: Code shout be 1000 and message should be \"OK\" when passing correctly (log in)");
		
		String email = "auto@gmail.com";	
		String password = "123456";
		
		SearchHelper search = new SearchHelper();
		String type, key;
		RandomString rdStr = new RandomString();
		RandomInteger rdInt = new RandomInteger();
		for(int i=0; i<5; i++) {
			type = Integer.toString(rdInt.getRandomInteger(1, 4));
			key = rdStr.getRandomNumericString(1);
			try {	
				Response response = search.getApiResponse(email,  password, type, key);
				Assert.assertEquals(search.getCodeResponse(response), 1000);
				Assert.assertEquals(search.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 1: Failed");
				System.out.println(type + "---" + key);
			}
		}
	}
	
	public void test2() {
		System.out.println("Test 2 in Search API: Code shout be 1000 and message should be \"OK\" when passing correctly (not log in)");
	
		SearchHelper search = new SearchHelper();
		String type, key;
		RandomString rdStr = new RandomString();
		RandomInteger rdInt = new RandomInteger();
		for(int i=0; i<5; i++) {
			type = Integer.toString(rdInt.getRandomInteger(1, 4));
			key = rdStr.getRandomNumericString(1);
			try {	
				Response response = search.getApiResponse(type, key);
				Assert.assertEquals(search.getCodeResponse(response), 1000);
				Assert.assertEquals(search.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test 2: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 2: Failed");
				System.out.println(type + "---" + key);
			}
		}
	}
	
	public void test3() {
		System.out.println("Test 3 in Search API: Code shout be 1000 and message should be \"OK\" when passing correctly (special character)(not log in)");
	
		SearchHelper search = new SearchHelper();
		String type, key;
		RandomInteger rdInt = new RandomInteger();
		for(int i=0; i<5; i++) {
			type = Integer.toString(rdInt.getRandomInteger(1, 4));
			key = " ";
			try {	
				Response response = search.getApiResponse(type, key);
				Assert.assertEquals(search.getCodeResponse(response), 1000);
				Assert.assertEquals(search.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test 3: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 3: Failed");
				System.out.println("Type is: " + type + " and key is: " + key + "-");
			}
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
