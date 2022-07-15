package apihelper;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import urlhelper.*;

public class InfoHelper extends GeneralHelper{

	public Response getApiResponse(String email, String password) {
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
						.given()
							.header("Authorization", "Bearer" + access_token)
						.when()
						 	.get("api/info");
		return response;
	}
	
	public Response getApiResponse() {	
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
						.given()
						.when()
						 	.get("api/info");
		return response;
	}
	
}
