package apitest;

import org.testng.Assert;

import java.util.ArrayList;

import apihelper.GetListAuctionsHelper;
import apihelper.GetListCommentsHelper;
import apihelper.TotalLikeOfAuctionHelper;
import io.restassured.response.Response;
import randomhelper.*;

public class TotalLikeOfAuctionTest {

	public void test1() {
		System.out.println("Test 1 in TotalLikeOfAuction Api: Code and Message should be 1000 and \"OK\" when passing corectly auctionId (login)");
		String email = "auto@gmail.com";
		String password = "123456";
		
		RandomInteger rdInt = new RandomInteger();
		TotalLikeOfAuctionHelper totalLike = new TotalLikeOfAuctionHelper();
		
		GetListAuctionsHelper listAuction = new GetListCommentsHelper();
		ArrayList<String> list = listAuction.getListAuctionId();
		String auctionId;
		
		for(int i=0; i<5; i++) {
			auctionId = "/" + list.get(rdInt.getRandomInteger(0, list.size()-1));
			try {
				Response response = totalLike.getApiResponse(email, password, auctionId);
				Assert.assertEquals(totalLike.getStatusCode(response), 200);
				Assert.assertEquals(totalLike.getCodeResponse(response), 1000);
				Assert.assertEquals(totalLike.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test1: Passed");
			} catch(AssertionError e){
				System.out.println("Unit " + i + " in test1: Failed");
			}
		}
		System.out.println("Test 1 finished");
	}
	
	public void test2() {
		System.out.println("Test 2 in TotalLikeOfAuction Api: Code and Message should be 1000 and \"OK\" when passing corectly auctionId (not-login)");

		RandomInteger rdInt = new RandomInteger();
		TotalLikeOfAuctionHelper totalLike = new TotalLikeOfAuctionHelper();
		
		GetListAuctionsHelper listAuction = new GetListCommentsHelper();
		ArrayList<String> list = listAuction.getListAuctionId();
		String auctionId;
		
		for(int i=0; i<5; i++) {
			auctionId = "/" + list.get(rdInt.getRandomInteger(0, list.size()-1));
			try {
				Response response = totalLike.getApiResponse(auctionId);
				Assert.assertEquals(totalLike.getStatusCode(response), 200);
				Assert.assertEquals(totalLike.getCodeResponse(response), 1000);
				Assert.assertEquals(totalLike.getMessageResponse(response), "OK");
				System.out.println("Unit " + i + " in test2: Passed");
			} catch(AssertionError e){
				System.out.println("Unit " + i + " in test2: Failed");
			}
		}
		System.out.println("Test 2 finished");
	}
	
	public void test3() {
		System.out.println("Test 3 in TotalLikeOfAuction Api: Status code should be 404 when passing auctionId that not exist (not-login)");

		RandomInteger rdInt = new RandomInteger();
		TotalLikeOfAuctionHelper totalLike = new TotalLikeOfAuctionHelper();
		
		GetListAuctionsHelper listAuction = new GetListCommentsHelper();
		ArrayList<String> list = listAuction.getListAuctionId();
		String auctionId;
		
		for(int i=0; i<5; i++) {
			auctionId = "/" + Integer.toString(Integer.parseInt(list.get(rdInt.getRandomInteger(0, list.size()-1)))*100);
			try {
				Response response = totalLike.getApiResponse(auctionId);
				Assert.assertEquals(totalLike.getStatusCode(response), 404);
				System.out.println("Unit " + i + " in test3: Passed");
			} catch(AssertionError e){
				System.out.println("Unit " + i + " in test3: Failed");
				System.out.println(auctionId);
			}
		}
		System.out.println("Test 3 finished");
	}
	
	public void test4() {
		System.out.println("Test 4 in TotalLikeOfAuction Api: Status code should be 500 when passing auctionId that cannot convert to Integer or out of range Integer(not-login)");

		RandomString rdStr = new RandomString();
		RandomInteger rdInt = new RandomInteger();
		TotalLikeOfAuctionHelper totalLike = new TotalLikeOfAuctionHelper();

		String auctionId;
		
		for(int i=0; i<5; i++) {
			auctionId = "/" + rdStr.getRandomString(rdInt.getRandomInteger(0, 2000));
			try {
				Response response = totalLike.getApiResponse(auctionId);
				Assert.assertEquals(totalLike.getStatusCode(response), 500);
				System.out.println("Unit " + i + " in test4: Passed");
			} catch(AssertionError e){
				System.out.println("Unit " + i + " in test4: Failed");
			}
		}
		System.out.println("Test 4 finished");
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
