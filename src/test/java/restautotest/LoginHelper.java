package restautotest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.response.Response;

public class LoginHelper {

		private String access_token;
		
		public String getAccessToken(String email, String password) {
			try {
				String bodyResponse = this.getLoginResponse(email, password);
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
			}catch(JSONException e) {
				System.out.println("Error!!!");
				this.access_token = "";
			}
			return this.access_token;
		}
		
		public String getLoginResponse(String email, String password) { 
			JSONObject request = new JSONObject();
			request.put("email", email);
			request.put("password", password);
			
			baseURI = BaseURI.BASEURI;
			Response response = 
					given()
						.header("Content-Type", "application/json")
						.body(request.toString())
					.when()
						.post("api/login");
			return response.getBody().asString();
		}
				
}
