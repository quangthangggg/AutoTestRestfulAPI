package apitest;

import java.util.ArrayList;

import org.testng.Assert;

import apihelper.EditAuctionHelper;
import apihelper.GetListAuctionsHelper;
import io.restassured.response.Response;
import randomhelper.RandomDate;
import randomhelper.RandomInteger;
import randomhelper.RandomString;

public class EditAuctionTest {
	
	public void test1() {
		System.out.println("Test 1 in EditAuctions API: The code and message strings should be 1000 and \"OK\" when passing correctly :");	
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		String email = "auto@gmail.com", password = "123456", user_id = "456";
		ArrayList<String> listId = listAuctions.getListAuctionIdByUser(email, password, user_id);
		
		EditAuctionHelper editAuction = new EditAuctionHelper();
		String category_id, start_date, end_date, title_ni;
		
		RandomInteger rdInt = new RandomInteger();
		RandomString rdStr = new RandomString();
		RandomDate rdDate = new RandomDate();
		
		String auctionId;
		for(int i=0; i<5; i++) {
			auctionId = "/" + listId.get(rdInt.getRandomInteger(0, listId.size()-1));
			category_id = Integer.toString(rdInt.getRandomInteger(1, 7));
			start_date = rdDate.getRandomDate(); 
			end_date = start_date.substring(0, 8) + Integer.toString(Integer.parseInt(start_date.substring(8)) + 1);
			title_ni = rdStr.getRandomString(20);
			try {
				Response response = editAuction.getApiResponse(email, password, category_id, start_date, end_date, title_ni, auctionId);
				Assert.assertEquals(editAuction.getCodeResponse(response), 1000);
				Assert.assertEquals(editAuction.getMessageResponse(response), "OK");
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
		System.out.println("Test 2 in EditAuctions API: The code and message strings should be 1004 and \"まだログインではありません\" when not log in :");	
		
		EditAuctionHelper editAuction = new EditAuctionHelper();
		String category_id, start_date, end_date, title_ni;
		
		RandomInteger rdInt = new RandomInteger();
		RandomString rdStr = new RandomString();
		RandomDate rdDate = new RandomDate();
		
		String auctionId;
		for(int i=0; i<5; i++) {
			auctionId = "/" + rdInt.getRandomInteger(0, 10);
			category_id = Integer.toString(rdInt.getRandomInteger(1, 7));
			start_date = rdDate.getRandomDate(); 
			end_date = start_date.substring(0, 8) + Integer.toString(Integer.parseInt(start_date.substring(8)) + 1);
			title_ni = rdStr.getRandomString(20);
			try {
				Response response = editAuction.getEditAuctionResponse(category_id, start_date, end_date, title_ni, auctionId);
				Assert.assertEquals(editAuction.getCodeResponse(response), 1004);
				Assert.assertEquals(editAuction.getMessageResponse(response), "まだログインではありません");
		        System.out.println("Unit " + i + " in test 2: Passed");
			} catch (AssertionError e) {
				System.out.println("Unit " + i + " in test 2: Failed");
			} finally {
				System.out.println("Test 2 finished");
			}
		}
	}
	
//	public void test3() {
//		System.out.println("Test 3 in EditAuctions API: The code and message strings should be 1005 and \"Không thể chỉnh sửa\" when trying to edit approved auctions:");			
//		EditAuctionHelper editAuction = new EditAuctionHelper();
//		String category_id, start_date, end_date, title_ni;
//		
//		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
//		String email = "auto@gmail.com", password = "123456", user_id = "456";
//		ArrayList<String> listId = listAuctions.getListAuctionId();
//		ArrayList<String> listIdByUser = listAuctions.getListAuctionIdByUser(email, password, user_id);
//		
//		RandomInteger rdInt = new RandomInteger();
//		RandomString rdStr = new RandomString();
//		RandomDate rdDate = new RandomDate();
//		
//		String auctionId;
//		for(int i=0; i<5; i++) {
//			String temp = listId.get(rdInt.getRandomInteger(0, listId.size()-1));
//			auctionId = "/" + ((listIdByUser.contains(temp))?"4":temp);
//			category_id = Integer.toString(rdInt.getRandomInteger(1, 7));
//			start_date = rdDate.getRandomDate(); 
//			end_date = start_date.substring(0, 8) + Integer.toString(Integer.parseInt(start_date.substring(8)) + 1);
//			title_ni = rdStr.getRandomString(20);
//			try {
//				Response response = editAuction.getEditAuctionResponse(email, password, category_id, start_date, end_date, title_ni, auctionId);
//				Assert.assertEquals(editAuction.getCodeResponse(response), 1005);
//				Assert.assertEquals(editAuction.getMessageResponse(response), "Không thể chỉnh sửa");
//		        System.out.println("Unit " + i + " in test 3: Passed");
//			} catch (AssertionError e) {
//				System.out.println("Unit " + i + " in test 3: Failed");
//			}
//		}
//	}

	public void chooseTest(String select) {
		switch(select) {
		case "0": 
			this.test1();
			this.test2();
//			this.test3();
			break;
		case "1": 
			this.test1();
			break;
		case "2":
			this.test2();
			break;
//		case "3":
//			this.test3();
//			break;
		default:
			break;
		}
	}
}
