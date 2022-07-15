package apihelper;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.response.Response;

public abstract class GeneralHelper {
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
	public int getCodeResponse(Response response) {
		try {
			JSONObject res = new JSONObject(response.getBody().asString());
			return Integer.parseInt(res.get("code").toString());
		} catch(JSONException e) {
			System.out.println("Response is not as a JSON format");
			return 0;
		}

	}
	
	public String getMessageResponse(Response response) {
		try {
			JSONObject res = new JSONObject(response.getBody().asString());
			return res.get("message").toString();
		} catch(JSONException e) {
			System.out.println("Response is not as a JSON format");
			return "0";
		}

	}
	
	public String getDataResponse(Response response) {
		try {
			JSONObject res = new JSONObject(response.getBody().asString());
			return res.get("data").toString();			
		} catch(JSONException e) {
			System.out.println("Response is not as a JSON format");
			return "0";
		}

	}
}
