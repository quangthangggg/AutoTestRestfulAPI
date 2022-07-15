package apitest;

import org.testng.Assert;

import apihelper.GetListAuctionsHelper;
import io.restassured.response.Response;
import randomhelper.RandomInteger;
import randomhelper.RandomString;

public class GetListAuctionsTest {

	public void test1() {
		System.out.println("Test 1 in GetListAuctions API: The code and message strings should be 1000 and \"OK\" when passing correctly :");
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		RandomString rdStr = new RandomString();
		RandomInteger rdNum = new RandomInteger();
		String index, count, user_id, type, category_id, statusId;
		user_id = null;
		type = null;
		category_id = null;
		
		for(int i=0; i<5; i++) {
			index = rdStr.getRandomNumericString(10);
			count = rdStr.getRandomNumericString(10);
			statusId = "/" + rdNum.getRandomInteger(0, 6);
			try {
				Response response = listAuctions.getApiResponse(index, count, user_id, type, category_id, statusId);
				Assert.assertEquals(listAuctions.getCodeResponse(response), 1000);
				Assert.assertEquals(listAuctions.getMessageResponse(response), "OK");
		        System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 1: Failed");
			} finally {
				System.out.println("Test 1 finished");
			}
		}
	 }
	
	public void test2() {
		System.out.println("Nothing....");
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
