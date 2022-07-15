package restautotest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;

public class GetListAuctionsByUser extends APINeedTesting{
	
	public String setLogin(String email, String password) {
		LoginHelper lg = new LoginHelper();
		return lg.getAccessToken(email, password);
	}

	public String creRequest(String... request) {		
		JSONObject req = new JSONObject();
		req.put("index", request[0]);
		req.put("count", request[1]);
		return req.toString();
	}
	
	public void callAPI(String request, String statusID, String access_token) {
		baseURI = BaseURI.BASEURI;
		
		Response response = 
				given()
					.header("Authorization", "Bearer" + access_token)
					.contentType("application/json")
					.body(request)
				.when()
					.get("api/auctions/listAuctionsByUser/" + statusID);
		
		JSONObject rep = new JSONObject(response.getBody().asString());
		this.codeResponse = Integer.parseInt(rep.get("code").toString());
		this.messageResponse = rep.get("message").toString();
		this.dataResponse = rep.get("data").toString();
	}
	
	void test1() {
		System.out.println("Test 1 in GetListAuctionsByUser API: The code should be 1000 and message is OK when passing correctly");
		String email = "auto@gmail.com";	
		String password = "123456";
		String access_token = this.setLogin(email, password);
		//Unit 1
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);		
			this.callAPI(requestEdit, "1", access_token);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1: Failed");
		}
		
		//Unit 2
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "2", access_token);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 2: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 2: Failed");
		}
		
		//Unit 3
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "3", access_token);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 3: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 3: Failed");
		}
		
		//Unit 4
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "4", access_token);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 4: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 4: Failed");
		}
		
		//Unit 5
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "5", access_token);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 5: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 5: Failed");
		}
		
		//End
		System.out.println("Finish Test 1 in GetListAuctionsByUser");
	}
	
	void test2() {
		System.out.println("Test 2 in GetListAuctionsByUser API: Code and Message should be 1004 and \\\"まだログインではありません\\\" when not logged in yet");
		//Unit 1
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "1", "");
			Assert.assertEquals(this.codeResponse, 1004);
			Assert.assertEquals(this.messageResponse, "まだログインではありません");
	        System.out.println("Unit 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1: Failed");
		}
		
		//Unit 2
		try {		
			String requestEdit = this.creRequest(
					"2"
					, "mj"
			);
			this.callAPI(requestEdit, "2", "");
			Assert.assertEquals(this.codeResponse, 1004);
			Assert.assertEquals(this.messageResponse, "まだログインではありません");
	        System.out.println("Unit 2: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 2: Failed");
		}
		
		//Unit 3
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "3", "");
			Assert.assertEquals(this.codeResponse, 1004);
			Assert.assertEquals(this.messageResponse, "まだログインではありません");
	        System.out.println("Unit 3: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 3: Failed");
		}
		
		//Unit 4
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "4", "");
			Assert.assertEquals(this.codeResponse, 1004);
			Assert.assertEquals(this.messageResponse, "まだログインではありません");
	        System.out.println("Unit 4: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 4: Failed");
		}
		
		//Unit 5
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "5", "");
			Assert.assertEquals(this.codeResponse, 1004);
			Assert.assertEquals(this.messageResponse, "まだログインではありません");
	        System.out.println("Unit 5: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 5: Failed");
		}
		
		//End
		System.out.println("Finish Test 2 in GetListAuctionsByUser");
	}
	
	public static void main(String[] args) {
		GetListAuctionsByUser a = new GetListAuctionsByUser();
		a.test2();
	}
}
