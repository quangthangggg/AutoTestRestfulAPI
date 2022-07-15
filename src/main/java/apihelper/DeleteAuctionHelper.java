package apihelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class DeleteAuctionHelper extends GeneralHelper{
	
	public Response getApiResponse(String email, String password, String auctionId) {
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
						.given()
							.header("Authorization", "Bearer" + access_token)
						.when()
						 	.post("api/auctions/deleteAuction" + auctionId);
		if(response.getStatusCode() == 302) {
			Response firstResponse = RestAssured
					.given()
			            .redirects().follow(false)
			        .expect().statusCode(302)
					.when()
						.post("api/auctions/deleteAuction" + auctionId);
			String redirectUrl = firstResponse.getHeader("Location");
			Response response2 = RestAssured
			        .given()
			        .when().
			            get(redirectUrl);
			return response2;
		}
		return response;
	}
	
	public Response getApiResponse(String auctionId) {
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response firstResponse = RestAssured
				.given()
		            .redirects().follow(false)
		        .expect().statusCode(302)
				.when()
					.post("api/auctions/deleteAuction" + auctionId);
		String redirectUrl = firstResponse.getHeader("Location");
		Response response = RestAssured
		        .given()
		        .when().
		            get(redirectUrl);
		return response;
	}
}
