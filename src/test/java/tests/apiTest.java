package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pages.api;

public class apiTest {
	
	@Test // Testing a basic request status code
	public void testResponsecode() {
		
		Response resp = RestAssured.get(api.user_url);
		int code = resp.getStatusCode();
		Assert.assertEquals(code, 200);
		System.out.print("Status code is "+code+ " / ");
	}
	
	@Test // Testing response time
	public void testResponsetime() {
		
		Response resp = RestAssured.get(api.user_url);
		long time = resp.getTime();
		Assert.assertTrue(time < 1000);
		System.out.print("Response time is "+time+ " / ");
	}
	
	@Test
	public void testResponsevalue() {
		
		Response resp = RestAssured.get(api.user_url);
		String payload = resp.asString();
		System.out.print(payload);
	}
}
