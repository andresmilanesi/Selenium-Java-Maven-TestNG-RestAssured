package pages;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page_login {
	private WebDriver driver;
	private By userField;
	private By passField;
	private By signinButton;
	public static final String base_url = "http://newtours.demoaut.com/";
	
	public page_login(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passField = By.name("password");
		signinButton = By.name("login");
	}
	
	public void login(String user, String pass) {
		driver.navigate().to(base_url);
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passField).sendKeys(pass);
		driver.findElement(signinButton).click();
	}
}
