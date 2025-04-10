package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class f_defectStatus {

	public WebDriver driver;

	public f_defectStatus(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"Defect Status\"]")
	WebElement DefectStatus;

	@FindBy(xpath = "//button[text()=\" Add\"]")
	WebElement add;

	@FindBy(id = "statusName")
	WebElement statusName;

	@FindBy(xpath="//select[@id=\"label\"]")
	WebElement dropDownStatus;
	
	@FindBy(id = "addStatusBtn")
	WebElement saveBtn;

	@FindBy(xpath = "(//button[text()=\" Cancel\"])[1]")
	WebElement cancelBtn;

	@FindBy(xpath = "//div[text()=\"Please enter a name for status!\"]")
	WebElement toastmsg;

	@FindBy(xpath = "(//button[@aria-label=\"Close\"])[2]")
	WebElement closeBtn;

	@FindBy(xpath = "//button[text()=\" Edit\"]")
	WebElement edit;

	@FindBy(xpath = "(//button[text()=\" Delete\"])[1]")
	WebElement delete;

	public void addDefectStatusName(String sname) {
		try {
			DefectStatus.click();
			add.click();
			driver.getWindowHandle();
			statusName.sendKeys(sname);
			saveBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchWithEditRecordFromTable(String search, String update) throws InterruptedException {

		Thread.sleep(5000);
		DefectStatus.click();
		driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(search);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='statusTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			boolean isFound = false;

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals(search)) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath("//*[@id=\"statusList\"]/tr[1]/td[4]/a[1]/button"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("statusName_edit")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("statusName_edit")).click();
						driver.findElement(By.id("statusName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("statusName_edit")).sendKeys(update);
					}
					driver.findElement(By.id("updateStatusBtn")).click();
					System.out.println("Data updated successfully for Welcome!");
					
				}
				break; // Exit after updating the row
			}
			if (!isFound) {
				System.out.println("Value '" + search + "' not found in the table.");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void searchWithDeleteRecordFromTable(String search) throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		
		WebElement searchValue=driver.findElement(By.xpath("//input[@type=\"search\"]"));
		searchValue.sendKeys(search);
		driver.findElement(By.xpath("//a[@aria-current=\"page\"]")).click();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='statusTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			boolean isFound = false;

			for (int i = 1; i <rows.size();i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals(search)) {
					// Update the input field value
					WebElement ageInput =cells.get(2).findElement(By.xpath("//*[@id=\"statusList\"]/tr[1]/td[4]/a[2]/button"));
					ageInput.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deleteStatusBtn")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("deleteStatusBtn")).click();
					}
					Thread.sleep(5000);
					System.out.println("Data updated successfully for Welcome!");
				}
				break; // Exit after updating the row
			}
			 if (!isFound) {
	                System.out.println("Value '" + searchValue + "' not found in the table.");
	            }

		} catch (Exception e) {
			
			 e.printStackTrace();
		}
	}
	
	public String blankValidation_popup() throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		add.click();
		driver.getWindowHandle();
		saveBtn.click();
		closeBtn.click();
		String msg = toastmsg.getText();
		System.out.println(msg);
		return msg;

	}

	public void editDefectStatusName() throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='statusTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i <rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals("New")) {
					// Update the input field value
					Thread.sleep(2000);
					WebElement ageInput =cells.get(2).findElement(By.xpath(".//button[text()=\" Edit\"]"));
					ageInput.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("statusName_edit")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("statusName_edit")).click();
						driver.findElement(By.id("statusName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("statusName_edit")).sendKeys("Open");
					}
					driver.findElement(By.id("updateStatusBtn")).click();
					System.out.println("Data updated successfully for Welcome!");
					break; // Exit after updating the row
				}
			}

		} catch (Exception e) {
			
			 e.printStackTrace();
		}
	}
	
	public void deleteRecordFromTable() throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='statusTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i <rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals("New")) {
					// Update the input field value
					WebDriverWait wait1 = new WebDriverWait(driver, 10); // 10 seconds timeout

					// Locate the cell and then find the "Delete" button within it
					WebElement ageInput = cells.get(2);
					WebElement deleteButton = wait1.until(ExpectedConditions.elementToBeClickable(ageInput.findElement(By.xpath(".//button[text()=' Delete']"))));

					// Click the "Delete" button
					deleteButton.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deleteStatusBtn")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("deleteStatusBtn")).click();
					}
					Thread.sleep(5000);
					System.out.println("Data updated successfully for Welcome!");
					break; // Exit after updating the row
				}
			}

		} catch (Exception e) {
			
			 e.printStackTrace();
		}
	}
	
	public void specialCharacterNotAllowed(String StatusName1) throws InterruptedException {
		Thread.sleep(3000);
		DefectStatus.click();

		add.click();
		driver.getWindowHandle();
		Thread.sleep(3000);
		statusName.sendKeys(StatusName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Special characters are not allowed!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
		
	}
	
	public void numericValueNotAllowed(String StatusName1) throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		
		add.click();
		driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		statusName.sendKeys(StatusName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Numbers not allowed in the start!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
	}
	
	public void alphabitWithSpecialCharacterNotAllowed(String CategoryName1) throws InterruptedException {
			
		Thread.sleep(5000);
		DefectStatus.click();

		add.click();
		driver.getWindowHandle();
		statusName.sendKeys(CategoryName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Special characters are not allowed!", ActualResult, "Expected result not met");
		}
	
	public void blankValidation(String StatusName1) throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		
		add.click();
		driver.getWindowHandle();
		statusName.sendKeys(StatusName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Please enter a name for status!", ActualResult, "Expected result not met");
		}
	
	public void ExceedingLimitVerification(String StatusName1) throws InterruptedException {
		Thread.sleep(2000);
		DefectStatus.click();
		
		add.click();
		driver.getWindowHandle();
		Thread.sleep(3000);
		statusName.sendKeys(StatusName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Status name must be within 30 characters!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
		}
	
	public void leadingSpaceVerification(String StatusName1) throws InterruptedException {
		Thread.sleep(5000);
		DefectStatus.click();
		
		add.click();
		driver.getWindowHandle();
		statusName.sendKeys(StatusName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Please enter a name for status!", ActualResult, "Expected result not met");
		Thread.sleep(3000);	
		}
}
