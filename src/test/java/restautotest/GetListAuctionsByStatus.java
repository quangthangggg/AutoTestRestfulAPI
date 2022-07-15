package restautotest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;

public class GetListAuctionsByStatus extends APINeedTesting{

	private String access_token;
	
	public void getAccessToken(String email, String password) {
		baseURI = BaseURI.BASEURI;
		
		LogInTest login = new LogInTest();
		String currentAccount = login.creRequest(email, password);
		login.callAPI(currentAccount);
		JSONObject data = new JSONObject(login.dataResponse);
		String access_token = data.getString("access_token").toString();
		this.access_token = access_token;
	}
	
	public String creRequest(String... request) {		
		JSONObject req = new JSONObject();
		req.put("index", request[0]);
		req.put("count", request[1]);
		return req.toString();
	}
	
	public void callAPI(String request, String statusID) {
		baseURI = BaseURI.BASEURI;
		
		Response response = 
				given()
					.header("Authorization", "Bearer" + this.access_token)
					.contentType("application/json")
					.body(request)
				.when()
					.get("api/auctions/listAuctionsByStatus" + statusID);
		
		JSONObject rep = new JSONObject(response.getBody().asString());
		this.codeResponse = Integer.parseInt(rep.get("code").toString());
		this.messageResponse = rep.get("message").toString();
		this.dataResponse = rep.get("data").toString();
	}
	
	void test1() {
		System.out.println("Test 1 in GetListAuctionsByStatus API: The code should be 1000 and message is OK when passing correctly");
		String email = "auto@gmail.com";	
		String password = "123456";
		this.getAccessToken(email, password);
		//Unit 1
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
			);
			this.callAPI(requestEdit, "/1");
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
			this.callAPI(requestEdit, "/2");
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
			this.callAPI(requestEdit, "/3");
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
			this.callAPI(requestEdit, "/4");
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
			this.callAPI(requestEdit, "/5");
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 5: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 5: Failed");
		}
		
		//End
		System.out.println("Finish Test 1 in GetListAuctionsByStatus");
	}

}
