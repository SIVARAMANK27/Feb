package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class b_loginPage {

	private WebDriver driver;

	@FindBy(id = "userEmail")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "loginBtn")
	private WebElement loginButton;

	public b_loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void validCrdential(String username, String password) {

		usernameField.clear();
		passwordField.clear();

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void invalidCredential(String username, String password) {
		usernameField.clear();
		passwordField.clear();

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	
	public void balnkCredential(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		usernameField.click();
	}
}
