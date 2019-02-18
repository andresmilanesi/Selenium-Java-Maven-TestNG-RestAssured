package tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import helpers.helpers;
import pages.page_login;
import pages.page_reservation;


public class Tests {
	private WebDriver driver;
	
	@BeforeMethod // What we are going to run before tests execution
	
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod // What we are going to run after the tests execution
	
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@Test(priority=1) 
	// Test website navigation
	
	public void test_navigation() {
		driver.navigate().to(page_login.base_url);
		helpers helper = new helpers();
		helper.waitForLoadComplete(driver);
		Assert.assertTrue(driver.getTitle().equals("Welcome: Mercury Tours"));
		System.out.print("Navigation went as expected. ");
	}
	
	@Test(priority=2) 
	// Test a valid login
	
	public void test_valid_login() {
		page_login login = new page_login(driver);
		login.login("mercury", "mercury");
		Assert.assertTrue(driver.getTitle().contains("Find a Flight"));
	}
	
	@Test(priority=4) 
	// Test an invalid login
	
	public void test_invalid_login() {
		page_login login = new page_login(driver);
		login.login("nonexisting", "user");
		Assert.assertTrue(driver.getTitle().equals("Sign-on: Mercury Tours"));
	}
	
	@Test(priority=3, dependsOnMethods = ("test_valid_login")) 
	// Test a flight reservation depending on successfull login
	
	public void flightReservation() {
		page_login login = new page_login(driver);
		login.login("mercury", "mercury");
		page_reservation pageReservation = new page_reservation(driver);
		pageReservation.selectPassengers(2);
		pageReservation.selectFrom("London");
		pageReservation.selectStartDate("April", 1);
		pageReservation.selectTo("Paris");
		pageReservation.selectEndDate("December", 1);
		pageReservation.submitReservation();
		pageReservation.assertReservationText2();		
	}

}
