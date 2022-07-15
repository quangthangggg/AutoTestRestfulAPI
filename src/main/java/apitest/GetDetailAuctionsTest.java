package apitest;

import org.testng.Assert;
import java.util.ArrayList;

import apihelper.GetDetailAuctionsHelper;
import apihelper.GetListAuctionsHelper;
import io.restassured.response.Response;
import randomhelper.RandomInteger;


public class GetDetailAuctionsTest {

	public void test1() {
		System.out.println("Test 1 in GetDetailAuctions API: The code and message strings should be 1000 and \"OK\" when passing correctly :");
		GetDetailAuctionsHelper detailAuctions = new GetDetailAuctionsHelper();
		
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		ArrayList<String> listId = listAuctions.getListAuctionId();
		
		RandomInteger rdNum = new RandomInteger();
		String auctionId;
		for(int i=0; i<5; i++) {
			auctionId = "/" + listId.get(rdNum.getRandomInteger(0, listId.size()-1));
			try {
				Response response = detailAuctions.getApiResponse(auctionId);
				Assert.assertEquals(detailAuctions.getCodeResponse(response), 1000);
				Assert.assertEquals(detailAuctions.getMessageResponse(response), "OK");
		        System.out.println("Unit " + i + " in test 1: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 1: Failed");
				System.out.println("Failed test has a auctionId is: " + auctionId);
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
