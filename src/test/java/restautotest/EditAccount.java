package restautotest;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.Assert;

public class EditAccount extends APINeedTesting{

	public String creRequest(String... request) {		
		JSONObject req = new JSONObject();
		req.put("email", request[0]);
		req.put("password", request[1]);
		req.put("re_pass", request[2]);
		req.put("address", request[3]);
		req.put("name", request[4]);
		req.put("phone", request[5]);
		req.put("avatar", request[6]);
		return req.toString();
	}
	
	public void callAPI(String oldEmail, String oldPassword, String request) {
		baseURI = BaseURI.BASEURI;
		
		LogInTest login = new LogInTest();
		String currentAccount = login.creRequest(oldEmail, oldPassword);
		login.callAPI(currentAccount);
		
		JSONObject data = new JSONObject(login.dataResponse);
		String access_token = data.getString("access_token").toString();
		Response response = 
				given()
					.header("Authorization", "Bearer" + access_token)
					.contentType("application/json")
					.body(request)
				.when()
					.post("api/edit");
		
		JSONObject rep = new JSONObject(response.getBody().asString());
		this.codeResponse = Integer.parseInt(rep.get("code").toString());
		this.messageResponse = rep.get("message").toString();
		this.dataResponse = rep.get("data").toString();
	}
	
	void test1() {
		System.out.println("Test 1 in EditAccount API: The code should be 1000 and message is OK:");
		
		//Unit 1
		try {		
			String requestEdit = this.creRequest(
					"1@gmail.com"
					, "123456"
					, "123456"
					, "BK"
					, "Dam Anh"
					, "123"
					, ""
			);
			String oldEmail = "0@gmail.com";	
			String oldPassword = "123456";
			this.callAPI(oldEmail, oldPassword, requestEdit);
			Assert.assertEquals(this.codeResponse, 1000);
			Assert.assertEquals(this.messageResponse, "OK");
	        System.out.println("Unit 1: Passed");
		} catch (AssertionError e) {
			System.out.println("Unit 1: Failed");
		}
	}
}
