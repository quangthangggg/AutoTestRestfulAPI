package apihelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class ReadNotificationsHelper extends GeneralHelper{

	public Response getApiResponse(String email, String password, String auction_deny_id) {
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Authorization", "Bearer" + access_token)
				.when()
					.get("api/notifications/read" + auction_deny_id);
		
		return response;
	}
}
