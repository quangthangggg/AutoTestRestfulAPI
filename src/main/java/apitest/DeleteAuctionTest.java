package apitest;

import java.util.ArrayList;

import org.testng.Assert;

import apihelper.DeleteAuctionHelper;
import apihelper.GetListAuctionsHelper;
import io.restassured.response.Response;
import randomhelper.RandomInteger;

public class DeleteAuctionTest {
	
	public void test1() {
		System.out.println("Test 1 in DeleteAuction API: Code and message should be 1000 and \"OK\" when perform correctly");
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetListAuctionsHelper getList = new GetListAuctionsHelper();
		ArrayList<String> list = getList.getListAuctionIdByUser(email, password, "456");
		
		RandomInteger rdInt = new RandomInteger();
		
		DeleteAuctionHelper delAuction = new DeleteAuctionHelper();
		String auctionId;
		int index;
		
		for(int i=0; i<5; i++) {
			index = rdInt.getRandomInteger(0, list.size()-1);
			auctionId = "/" + list.get(index);
			list.remove(index);
			try {
				Response response = delAuction.getApiResponse(email, password, auctionId);
				Assert.assertEquals(delAuction.getStatusCode(response), 200);
				Assert.assertEquals(delAuction.getCodeResponse(response), 1000);
				Assert.assertEquals(delAuction.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test1: Passed");
			} catch(AssertionError e) {
				System.out.println("Unit " + i + " in test1: Failed");
			}
		}
		System.out.println("Test 1 Finished");
	}
	
	public void test2() {
		System.out.println("Test 2 in DeleteAuction API: Code and message should be 1004 when not login");
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetListAuctionsHelper getList = new GetListAuctionsHelper();
		ArrayList<String> list = getList.getListAuctionIdByUser(email, password, "456");
		
		RandomInteger rdInt = new RandomInteger();
		
		DeleteAuctionHelper delAuction = new DeleteAuctionHelper();
		String auctionId;
		int index;
		
		for(int i=0; i<5; i++) {
			index = rdInt.getRandomInteger(0, list.size()-1);
			auctionId = "/" + list.get(index);
			list.remove(index);
			Response response = delAuction.getApiResponse(auctionId);
			try {
				Assert.assertEquals(delAuction.getStatusCode(response), 200);
				Assert.assertEquals(delAuction.getCodeResponse(response), 1004);
				System.out.println("Unit " + i + " in test2: Passed");
			} catch(AssertionError e) {
				System.out.println("Unit " + i + " in test2: Failed");
			}
		}
		System.out.println("Test 2 Finished");
	}
	
	public void test3() {
		System.out.println("Test 3 in DeleteAuction API: Code and message should be 1004 when login incorrectly");
		String email = "-";
		String password = "-";
		
		GetListAuctionsHelper getList = new GetListAuctionsHelper();
		ArrayList<String> list = getList.getListAuctionIdByUser("auto@gmail.com", "123456", "456");
		
		RandomInteger rdInt = new RandomInteger();
		
		DeleteAuctionHelper delAuction = new DeleteAuctionHelper();
		String auctionId;
		int index;
		
		for(int i=0; i<5; i++) {
			index = rdInt.getRandomInteger(0, list.size()-1);
			auctionId = "/" + list.get(index);
			list.remove(index);
			Response response = delAuction.getApiResponse(email, password, auctionId);
			try {
				Assert.assertEquals(delAuction.getStatusCode(response), 200);
				Assert.assertEquals(delAuction.getCodeResponse(response), 1004);
				System.out.println("Unit " + i + " in test3: Passed");
			} catch(AssertionError e) {
				System.out.println("Unit " + i + " in test3: Failed");
			}
		}
		System.out.println("Test 3 Finished");
	}
	
	public void test4() {
		System.out.println("Test 4 in DeleteAuction API: Code and message should be 1006 when trying to delete other auctions");
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetListAuctionsHelper getList = new GetListAuctionsHelper();
		ArrayList<String> list = getList.getListAuctionIdByStatus("1");
		
		RandomInteger rdInt = new RandomInteger();
		
		DeleteAuctionHelper delAuction = new DeleteAuctionHelper();
		String auctionId;
		int index;
		
		for(int i=0; i<5; i++) {
			index = rdInt.getRandomInteger(0, list.size()-1);
			auctionId = "/" + list.get(index);
			list.remove(index);
			Response response = delAuction.getApiResponse(email, password, auctionId);
			try {
				Assert.assertEquals(delAuction.getStatusCode(response), 200);
				Assert.assertEquals(delAuction.getCodeResponse(response), 1006);
				System.out.println("Unit " + i + " in test4: Passed");
			} catch(AssertionError e) {
				System.out.println("Unit " + i + " in test4: Failed");
				System.out.println(auctionId);
				System.out.println(delAuction.getStatusCode(response));
				System.out.println(delAuction.getCodeResponse(response));
			}
		}
		System.out.println("Test 4 Finished");
	}
	
	public void chooseTest(String select) {
		switch(select) {
		case "0": 
			this.test1();
			this.test2();
			this.test3();
			this.test4();
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
		case "4":
			this.test4();
			break;
		default:
			break;
		}
	}
	
}
