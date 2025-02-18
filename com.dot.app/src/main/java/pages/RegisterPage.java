package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {

	private WebDriver driver;

	@FindBy(id = "teamName")
	private WebElement teamname;

	@FindBy(id = "userName")
	private WebElement userName;

	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "confirmPassword")
	private WebElement  confirmPasswordField;

	@FindBy(id = "companyName")
	private WebElement companyName;

	@FindBy(xpath = "//a[text()=\"Create an account\"]")
	private WebElement Createaccount;
	
	@FindBy(xpath = "//button[text()=\"Create Account\"]")
	private WebElement CreateAccount;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createanaccount() {
		Createaccount.click();
	}

	public void verfifyteamTextBox(String maxAllowedName) {
	teamname.clear();
	teamname.sendKeys(maxAllowedName);
	Assert.assertEquals(teamname.getAttribute("value").length(), 100, "Name field should allow 100 characters.");
	}
	
	public void verifyUserName(String username) {
		userName.sendKeys(username);
		userName.click();
	}
	
	public void usernameValidation(String username) {
		
		userName.sendKeys(username);
		WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();
		CreateAccount.click();
	}
	
	public void emptyValidationUsername(String team, String email) {
		teamname.sendKeys(team);
		userEmail.sendKeys(email);
		WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();
		CreateAccount.click();
	}
	
	public void validEmailAddress(String teamName, String username, String email) {
		teamname.sendKeys(teamName);
		userName.sendKeys(username);
		userEmail.sendKeys(email);
		WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();
		CreateAccount.click();
	}
	
	public void emptyvalidationOfEmail(String teamName, String username) {
		teamname.sendKeys(teamName);
		userName.sendKeys(username);
		WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();
		CreateAccount.click();
        Alert alert = driver.switchTo().alert();

        // Get the text of the alert
        String alertText = alert.getText();
        System.out.println("Alert message: " + alertText);
 
        // Accept (click OK) the alert
        alert.accept();
	}
	
	public void testPasswordValidation(String teamName, String username, String email, String Password, String ConfirmPassword, boolean expectedOutcome) {
	     teamname.sendKeys(teamName);
	     userName.sendKeys(username);
		 userEmail.sendKeys(email);
		 
		 passwordField.clear();
	     confirmPasswordField.clear();
	    passwordField.sendKeys(Password);
        confirmPasswordField.sendKeys(ConfirmPassword);
        
        WebElement targetElement = driver.findElement(By.xpath("//button[text()=\"Create Account\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement).click().perform();
        CreateAccount.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String expectedResult=driver.findElement(By.xpath("//div[text()=\"Please enter a company name\"]")).getText();
        System.out.println(" "+expectedResult+" ");
        Assert.assertEquals(expectedResult, "Please enter a company password", "Alert text mismatch");
        }
	}