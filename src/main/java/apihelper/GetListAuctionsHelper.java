package apihelper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

public class GetListAuctionsHelper extends GeneralHelper {
	
	public JSONObject setRequestObject(String index, String count, String user_id, String type, String category_id) {
		JSONObject request = new JSONObject();
		request.put("index", index);
		request.put("count", count);
		request.put("user_id", user_id);
		request.put("type", type);
		request.put("category_id", category_id);
		return request;
	}
	
//For list by user
	public Response getApiResponse(String email, String password, String index, String count, String user_id, String type, String category_id, String statusId) { 
		LoginHelper login = new LoginHelper();
		String access_token = login.getAccessToken(email, password);
		
		JSONObject request = this.setRequestObject(index, count, user_id, type, category_id);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Authorization", "Bearer" + access_token)
					.contentType("application/json")
					.body(request.toString())
				.when()
					.get("api/auctions" + statusId);
		return response;
	}

//For list by type or category_id or nothing
	public Response getApiResponse(String index, String count, String user_id, String type, String category_id, String statusId) { 
		JSONObject request = this.setRequestObject(index, count, user_id, type, category_id);
		
		BaseURI baseUri = new BaseURI();
		RestAssured.baseURI = baseUri.getBaseURI();
		
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
					.body(request.toString())
				.when()
					.get("api/auctions" + statusId);
		return response;
	}
	
	public ArrayList<String> getListAuctionId() {
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		Response response = listAuctions.getApiResponse("", "", null, null, null, "/0");
		JSONObject data = new JSONObject(listAuctions.getDataResponse(response));
		int total = Integer.parseInt(data.get("total").toString());
		JSONArray auctions = data.getJSONArray("auctions");
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<total; i++) {
			JSONObject temp = new JSONObject(auctions.get(i).toString());
			list.add(temp.get("auction_id").toString());
		}
		return list;
	}
	
	public ArrayList<String> getListAuctionIdByStatus(String statusId) {
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		Response response = listAuctions.getApiResponse("", "", null, null, null, "/" + statusId);
		JSONObject data = new JSONObject(listAuctions.getDataResponse(response));
		int total = Integer.parseInt(data.get("total").toString());
		JSONArray auctions = data.getJSONArray("auctions");
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<total; i++) {
			JSONObject temp = new JSONObject(auctions.get(i).toString());
			list.add(temp.get("auction_id").toString());
		}
		return list;
	}
	
	public ArrayList<String> getListAuctionIdByUser(String email, String password, String user_id) {
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		Response response = listAuctions.getApiResponse(email, password, "","",  "456", null, null, "/0");
		JSONObject data = new JSONObject(listAuctions.getDataResponse(response));
		int total = Integer.parseInt(data.get("total").toString());
		JSONArray auctions = data.getJSONArray("auctions");
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<total; i++) {
			JSONObject temp = new JSONObject(auctions.get(i).toString());
			list.add(temp.get("auction_id").toString());
		}
		return list;
	}
	
	public ArrayList<String> getListAuctionIdEnded(){
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		Response response = listAuctions.getApiResponse("", "", null, null, null, "/3");
		JSONObject data = new JSONObject(listAuctions.getDataResponse(response));
		int total = Integer.parseInt(data.get("total").toString());
		JSONArray auctions = data.getJSONArray("auctions");
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<total; i++) {
			JSONObject temp = new JSONObject(auctions.get(i).toString());
			list.add(temp.get("auction_id").toString());
		}
		return list;
	}
	
	public ArrayList<String> getListPendingAuctionId(){
		GetListAuctionsHelper listAuctions = new GetListAuctionsHelper();
		Response response = listAuctions.getApiResponse("auto@gmail.com", "123456", "","",  "456", null, null, "/4");
		JSONObject data = new JSONObject(listAuctions.getDataResponse(response));
		int total = Integer.parseInt(data.get("total").toString());
		JSONArray auctions = data.getJSONArray("auctions");
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<total; i++) {
			JSONObject temp = new JSONObject(auctions.get(i).toString());
			list.add(temp.get("auction_id").toString());
		}
		return list;
	}
	
}
