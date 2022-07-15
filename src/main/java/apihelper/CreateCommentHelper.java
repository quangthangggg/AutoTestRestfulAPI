package apihelper;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.*;

public class CreateCommentHelper extends GeneralHelper{

	public JSONObject setRequestObject(String content, String comment_last_id) {		
		JSONObject request = new JSONObject();
		request.put("content", content);
		request.put("comment_last_id", comment_last_id);
		return request;
	}
	
	public Response getApiResponse(String email, String password, String content, String comment_last_id, String auctionId) {
		JSONObject request = this.setRequestObject(content, comment_last_id);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		Response response = RestAssured
				.given()
					.header("Authorization", "Bearer" + access_token)
					.contentType("application/json")
					.body(request.toString())
				.when()
					.post("api/comments/create" + auctionId);
		return response;
	}

//For not login
	public Response getApiResponse(String content, String comment_last_id, String auctionId) {
		JSONObject request = this.setRequestObject(content, comment_last_id);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response firstResponse = RestAssured
				.given()
					.header("Content-Type", "application/json")
					.body(request.toString())
		            .redirects().follow(false)
				.when()
					.post("api/comments/create" + auctionId);
		
		String redirectUrl = firstResponse.getHeader("Location");
		Response response = RestAssured
		        .given()
		        	.header("Content-Type", "application/json")
		        .when().
		            get(redirectUrl);
		return response;
	}
}
