package apihelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import urlhelper.BaseURI;

import org.json.JSONObject;

public class LoginHelper extends GeneralHelper{

		private String access_token;
		
		public JSONObject setRequestObject(String email, String password) {
			JSONObject request = new JSONObject();
			request.put("email", email);
			request.put("password", password);
			return request;
		}
		
		public String getAccessToken(String email, String password) {
				String bodyResponse = this.getApiResponse(email, password).getBody().asString();
				JSONObject res = new JSONObject(bodyResponse);
				int code = Integer.parseInt(res.get("code").toString());
				if(code != 1000) {
					this.access_token = "";
				}else {
					String dataResponse = res.get("data").toString();
					JSONObject data = new JSONObject(dataResponse);
					String access_token = data.getString("access_token").toString();
					this.access_token = access_token;
				}
			return this.access_token;
		}
		
		public Response getApiResponse(String email, String password) { 
			JSONObject request = this.setRequestObject(email, password);
			
			BaseURI baseUri = new BaseURI();
			RestAssured.baseURI = baseUri.getBaseURI();
			
			Response response = RestAssured
					.given()
						.header("Content-Type", "application/json")
						.body(request.toString())
					.when()
						.post("api/login");
			return response;
		}
				
}

