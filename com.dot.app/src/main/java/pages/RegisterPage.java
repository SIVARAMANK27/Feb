package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	private WebDriver driver;
	
	@FindBy(id="teamName")
	private WebElement teamname; 
	
	@FindBy(id="userName")
	private WebElement userName; 
	
	@FindBy(id="userEmail")
	private WebElement userEmail; 
	
	@FindBy(id="password")
	private WebElement password; 
	
	@FindBy(id="confirmPassword")
	private WebElement confirmPassword; 
	
	@FindBy(id="companyName")
	private WebElement companyName; 
	
	@FindBy(xpath="//a[text()=\"Create an account\"]")
	private WebElement Createaccount; 
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createanaccount	() {
		Createaccount.click();
	}
}
