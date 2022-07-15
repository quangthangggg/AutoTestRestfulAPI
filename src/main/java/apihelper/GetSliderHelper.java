package apihelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class GetSliderHelper extends GeneralHelper{
	public Response getApiResponse(String email, String password) {
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Authorization", "Bearer" + access_token)
				.when()
					.get("api/slider");
		
		return response;
	}
	
	public Response getApiResponse() {	
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
				.when()
					.get("api/slider");
		
		return response;
	}
}
