package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pages.api;

public class apiTest2 {
	
	@Test // Assert status code from reponse
	public void test_response_status() {
		given().
		when().
			get(api.user_url).
		then().
			assertThat().statusCode(200);
	}
	
	@Test // Assert response time is less than certain value
	public void test_response_time() {
		given().
		when().
			get(api.user_url).
		then().
			time(lessThan(1000l));		
	}
	
	@Test // Validate total number of registers for certain field
	public void test_user_amout() {
		given().
		when().
			get(api.user_url).
		then().
			body("total", equalTo(12));
	}
	
	@Test // Test a post request with valid parameters get a 201 code
	public void test_post_parameters() {
		HashMap<String, String> postContent = new HashMap<>();
		postContent.put("name", "morpheus");
		postContent.put("job", "leader");
		
		given().
			contentType(ContentType.JSON).
		with().
			body(postContent).
		when().
			post(api.user_url).
		then().
			assertThat().statusCode(201);						
	}
}
