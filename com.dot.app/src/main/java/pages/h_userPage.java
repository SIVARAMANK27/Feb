package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class h_userPage {

	public WebDriver driver;

	public h_userPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"Users\"]")
	WebElement users;

	@FindBy(xpath = "//button[text()=\" Add\"]")
	WebElement add;

	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userRole")
	WebElement userRole;
	
	@FindBy(id = "addUserBtn")
	WebElement saveBtn;

	@FindBy(xpath = "(//button[text()=\" Cancel\"])[1]")
	WebElement cancelBtn;

	@FindBy(xpath = "//div[text()=\"Please enter an email for user!\"]")
	WebElement toastmsg;

	@FindBy(xpath = "(//button[@aria-label=\"Close\"])[2]")
	WebElement closeBtn;	
	
	public void navigateToAddUsers(String Email) throws InterruptedException {
		users.click();
		add.click();
		
		Select select=new Select(userRole);
		select.selectByIndex(1);
		Thread.sleep(5000);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toastMessage")));
		userEmail.sendKeys(Email);
		String messageText = toastMessage.getText();
		System.out.println("Toast Message: " + messageText);

		// Validate the toast message text
		Assert.assertEquals(messageText, "Please enter an email for user!");

	}
	
	public void printAllTableRecords() {
		users.click();
		// Locate the table
		WebElement table = driver.findElement(By.id("invitationTable"));
		
	
		// Get all headers
		List<WebElement> headers = table.findElements(By.xpath(".//thead/tr/th"));
		for (WebElement header : headers) {
		    System.out.print(header.getText() + "\t");
		}
		System.out.println("\n------------------------------");

		// Get all rows
		List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
		// Get the total count of rows
		int totalRows = rows.size();

		System.out.println("Total number of records: " + totalRows);
		for (WebElement row : rows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    for (WebElement cell : cells) {
		        System.out.print(cell.getText() + "\t");
		    }
		    System.out.println();
		}
	}
	
	public void emailWithoutDomainExtension(String email) throws InterruptedException {
		users.click();
		add.click();
		
		userEmail.sendKeys(email);
		Select select=new Select(userRole);
		select.selectByIndex(1);
		Thread.sleep(5000);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toastMessage")));
		String messageText = toastMessage.getText();
		System.out.println("Toast Message: " + messageText);

		// Validate the toast message text
		Assert.assertEquals(messageText, "Please enter a valid email!");
	}
	
	public void testEmailWithoutAtSymbol(String email) throws InterruptedException {
		users.click();
		add.click();
		
		userEmail.sendKeys(email);
		Select select=new Select(userRole);
		select.selectByIndex(1);
		Thread.sleep(5000);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toastMessage")));
		String messageText = toastMessage.getText();
		System.out.println("Toast Message: " + messageText);

		// Validate the toast message text
		Assert.assertEquals(messageText, "Please enter a valid email!");
	}
	
	public void testEmailWithOnlyAtSymbol(String email) throws InterruptedException {
		users.click();
		add.click();
		
		userEmail.sendKeys(email);
		Select select=new Select(userRole);
		select.selectByIndex(1);
		Thread.sleep(5000);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toastMessage")));
		String messageText = toastMessage.getText();
		System.out.println("Toast Message: " + messageText);

		// Validate the toast message text
		Assert.assertEquals(messageText, "Please enter a valid email!");
	}
	
	public void testEmptyEmailField(String email) throws InterruptedException {
		users.click();
		add.click();
		
		userEmail.sendKeys(email);
		Select select=new Select(userRole);
		select.selectByIndex(1);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toastMessage")));
		String messageText = toastMessage.getText();
		System.out.println("Toast Message: " + messageText);

		// Validate the toast message text
		Assert.assertEquals(messageText, "Please enter an email for user!");
	}
	
	public void testEmailWithSpaces(String email) throws InterruptedException {
		users.click();
		add.click();
		
		userEmail.sendKeys(email);
		Select select=new Select(userRole);
		select.selectByIndex(1);
		Thread.sleep(5000);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toastMessage")));
		String messageText = toastMessage.getText();
		System.out.println("Toast Message: " + messageText);

		// Validate the toast message text
		Assert.assertEquals(messageText, "Please enter a valid email!");
	}
}
