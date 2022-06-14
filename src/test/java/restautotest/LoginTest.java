package restautotest;

import static io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.*;
import io.restassured.http.ContentType;
 
public class LoginTest {
 

	public static void main(String args[]) { 
//		String BASE = "https://auction-app3.herokuapp.com/";
//		String BASE1 = "https://reqres.in/";
//		// Specify the base URL to the RESTful web service 
//		RestAssured.baseURI = BASE1 + "api/unknown";
//		// Get the RequestSpecification of the request to be sent to the server. 
//		RequestSpecification httpRequest = RestAssured.given(); 
//		// specify the method type (GET) and the parameters if any. 
//		//In this case the request does not take any parameters 
//		Response response = httpRequest.request(Method.GET, "");
//		// Print the status and message body of the response received from the server 
//		System.out.println("Status received => " + response.getStatusLine()); 
//		System.out.println("Response=>" + response.prettyPrint());;

		baseURI = "https://reqres.in/api"; 
		RequestSpecification request = given();
		
		JSONObject requestPrams = new JSONObject();
		requestPrams.put("name", "Raghav");
		requestPrams.put("job", "Teacher");
		request.header("Content-Type", "application/json");
		request.body(requestPrams.toJSONString());
		
		Response response = request.post("https://reqres.in/api/users");
		System.out.println("The content: " + response.getBody().asString());

//		    request.body(requestParams.toJSONString());
//		    Response response = request.put("/users"); 
//		    ResponseBody body = response.getBody();
//		    System.out.println(response.getStatusLine());
//		    System.out.println(body.asString());
		    
	 }
}