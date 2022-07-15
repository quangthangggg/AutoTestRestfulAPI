package apihelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class GetDetailAuctionsHelper extends GeneralHelper {
	
	public Response getApiResponse(String auctionId) { 
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
				.when()
					.get("api/auctions/detail" + auctionId);
		return response;
	}
	
}
