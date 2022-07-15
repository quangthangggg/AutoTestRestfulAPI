package apihelper;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class SearchHelper extends GeneralHelper{
	public JSONObject setRequestObject(String type, String key) {		
		JSONObject request = new JSONObject();
		request.put("type", type);
		request.put("key", key);
		return request;
	}
	
	public Response getApiResponse(String email, String password, String type, String key) {
		JSONObject request = this.setRequestObject(type, key);
		
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Authorization", "Bearer" + access_token)
					.contentType("application/json")
					.body(request.toString())
				.when()
					.get("api/search");
		
		return response;
	}
//for not log in
	public Response getApiResponse(String type, String key) {
		JSONObject request = this.setRequestObject(type, key);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
					.body(request.toString())
				.when()
					.get("api/search");
		return response;
	}
	
}
