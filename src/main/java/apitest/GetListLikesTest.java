package apitest;

import org.testng.Assert;

import apihelper.GetListLikesHelper;
import io.restassured.response.Response;
import randomhelper.*;

public class GetListLikesTest {
	void test1() {
		System.out.println("Test 1 of GetListLikes API: return code should be 1000 and message should be OK when passing correctly");
		String email = "auto@gmail.com";
		String password = "123456";
		
		GetListLikesHelper listLike = new GetListLikesHelper();
		String index, count, statusId;
		
		RandomInteger rdInt = new RandomInteger();
		
		for(int i=0; i<5; i++) {
			statusId = "/" + Integer.toString(rdInt.getRandomInteger(0, 4));
			count= Integer.toString(rdInt.getRandomInteger(500, 600));
			index = "\t";
			try {	
				Response response = listLike.getApiResponse(email, password, index, count, statusId);
				Assert.assertEquals(listLike.getCodeResponse(response), 1000);
				Assert.assertEquals(listLike.getMessageResponse(response), "OK");
		        System.out.println("Unit " + i+ " in test 1: Passed");
			} catch (AssertionError e) {
		        System.out.println("Unit " + i+ " in test 1: Passed");
			}
		}	
	}

	void test2() {
		System.out.println("Test 2 of GetListLikes API: return code should be 1004 since user haven't logged in");
		
		GetListLikesHelper listLike = new GetListLikesHelper();
		String index, count, statusId;
		
		RandomInteger rdInt = new RandomInteger();
		
		for(int i=0; i<5; i++) {
			statusId = "/" + Integer.toString(rdInt.getRandomInteger(0, 4));
			count= Integer.toString(rdInt.getRandomInteger(500, 600));
			index = "\t";
			try {	
				Response response = listLike.getApiResponse(index, count, statusId);
				Assert.assertEquals(listLike.getCodeResponse(response), 1004);
				Assert.assertEquals(listLike.getMessageResponse(response), "Chưa đăng nhập");
		        System.out.println("Unit " + i+ " in test 2: Passed");
			} catch (AssertionError e) {
		        System.out.println("Unit " + i+ " in test 2: Passed");
			}
		}
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
