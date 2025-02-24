package restautotest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateAuction extends APINeedTesting{

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
		req.put("category_id", request[0]);
		req.put("start_date", request[1]);
		req.put("end_date", request[2]);
		req.put("title_ni", request[3]);
		return req.toString();
	}

	public void callAPI(String request) {
		baseURI = BaseURI.BASEURI;
		
		Response response = 
				given()
					.header("Authorization", "Bearer" + this.access_token)
					.contentType("application/json")
					.body(request)
				.when()
					.post("api/auctions/create");
		
		JSONObject rep = new JSONObject(response.getBody().toString());
		this.codeResponse = Integer.parseInt(rep.get("code").toString());
		this.messageResponse = rep.get("message").toString();
		this.dataResponse = rep.get("data").toString();
	}
	
	void test1() {
		System.out.println("Test 1 in CreateAuction API: Code and Message should be 1000 and \"OK\" when passing fully and correctly");
		
		String email = "auto@gmail.com";	
		String password = "123456";
		this.getAccessToken(email, password);
		//Unit 1
		try {		
			String requestEdit = this.creRequest(
					"1"
					, "2022/07/05"
					, "2022/07/09"
					, "auctsss"
			);
			this.callAPI(requestEdit);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1: Failed");
		}
	}
	
	void test2() {
		System.out.println("Test 2 in CreateAuction API: Code and Message should be 1004 and \"まだログインではありません\" when not logged in yet");
		this.access_token = "";
		//Unit 1
		try {		
			String requestEdit = this.creRequest(
					""
					, ""
					, ""
					, ""
			);
			this.callAPI(requestEdit);
			Assert.assertEquals(this.codeResponse, 1004);
			Assert.assertEquals(this.messageResponse, "まだログインではありません");
	        System.out.println("Unit 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1: Failed");
		}
	}
	
	public static void main(String[] args) {
		CreateAuction c = new CreateAuction();
		c.test2();
	}

}
