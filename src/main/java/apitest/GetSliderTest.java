package apitest;

import org.testng.Assert;

import apihelper.GetSliderHelper;
import io.restassured.response.Response;

public class GetSliderTest {

	public void test1() {
		System.out.println("Test 1 in GetSlider API: Code and message should be 1000 and \"OK\" respectively (login correctly)");
		GetSliderHelper getSlider = new GetSliderHelper();
		String email = "auto@gmail.com";
		String password = "123456";
		try {
			Response response = getSlider.getApiResponse(email, password);
			Assert.assertEquals(getSlider.getStatusCode(response), 200);
			Assert.assertEquals(getSlider.getCodeResponse(response), 1000);
			Assert.assertEquals(getSlider.getMessageResponse(response), "OK");
			System.out.println("Test 1: Passed");
		}catch(AssertionError e) {
			System.out.println("Test 1: Failed");
		}
		System.out.println("Test 3: Finished");
	}
	
	public void test2() {
		System.out.println("Test 2 in GetSlider API: Code and message should be 1000 and \"OK\" respectively (login incorrectly)");
		GetSliderHelper getSlider = new GetSliderHelper();
		String email = "-";
		String password = "-";
		try {
			Response response = getSlider.getApiResponse(email, password);
			Assert.assertEquals(getSlider.getStatusCode(response), 200);
			Assert.assertEquals(getSlider.getCodeResponse(response), 1000);
			Assert.assertEquals(getSlider.getMessageResponse(response), "OK");
			System.out.println("Test 2: Passed");
		}catch(AssertionError e) {
			System.out.println("Test 2: Failed");
		}
		System.out.println("Test 3: Finished");
	}
	
	public void test3() {
		System.out.println("Test 3 in GetSlider API: Code and message should be 1000 and \"OK\" respectively (not login)");
		GetSliderHelper getSlider = new GetSliderHelper();
		try {
			Response response = getSlider.getApiResponse();
			Assert.assertEquals(getSlider.getStatusCode(response), 200);
			Assert.assertEquals(getSlider.getCodeResponse(response), 1000);
			Assert.assertEquals(getSlider.getMessageResponse(response), "OK");
			System.out.println("Test 3: Passed");
		}catch(AssertionError e) {
			System.out.println("Test 3: Failed");
		}
		System.out.println("Test 3: Finished");
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
