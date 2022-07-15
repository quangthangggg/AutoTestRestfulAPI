package apihelper;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class GetListLikesHelper extends GeneralHelper {
	
	public JSONObject setRequestObject(String index, String count) {
		JSONObject req = new JSONObject();
		req.put("index", index);
		req.put("count", count);
		return req;
	}
	
	public Response getApiResponse(String email, String password, String index, String count, String statusId) {
		JSONObject request = this.setRequestObject(index, count);
		
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();;
		
		Response response = RestAssured
				.given()
					.header("Authorization", "Bearer" + access_token)
					.contentType("application/json")
					.body(request.toString())
				.when()
					.get("api/likes" + statusId);
		
		return response;
	}
	
	public Response getApiResponse(String index, String count, String statusId) {
		JSONObject request = this.setRequestObject(index, count);
	
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();;
		
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
					.body(request.toString())
				.when()
					.get("api/likes" + statusId);
		
		return response;
	}
}
