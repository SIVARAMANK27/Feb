package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class g_defectPriority {

	public WebDriver driver;

	public g_defectPriority(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"Defect Priority\"]")
	WebElement defectPriority;

	@FindBy(xpath = "//button[text()=\" Add\"]")
	WebElement add;

	@FindBy(id = "priorityName")
	WebElement priorityName;

	@FindBy(id = "addPriorityBtn")
	WebElement saveBtn;

	@FindBy(xpath = "(//button[text()=\" Cancel\"])[1]")
	WebElement cancelBtn;

	@FindBy(xpath = "//div[text()=\"Please enter a name for category!\"]")
	WebElement toastmsg;

	@FindBy(xpath = "(//button[@aria-label=\"Close\"])[2]")
	WebElement closeBtn;

	@FindBy(xpath = "//button[text()=\" Edit\"]")
	WebElement edit;

	@FindBy(xpath = "(//button[text()=\" Delete\"])[1]")
	WebElement delete;

	public void addDefectPriorityName(String name) {
		try {
			defectPriority.click();
			add.click();
			driver.getWindowHandle();
			priorityName.sendKeys(name);
			saveBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchWithEditRecordFromTable(String search, String update) throws InterruptedException {
		WebElement sidebar = driver.findElement(By.id("sidebar")); // Replace with actual locator
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", sidebar);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(defectPriority)).click();
		driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(search);
		
		Thread.sleep(5000);
		try {
			
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='prioritiesTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			  boolean isFound = false;

			for (int i = 1; i <rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals(search)) {
					// Update the input field value
					WebElement ageInput =cells.get(2).findElement(By.xpath(".//button[text()=\" Edit\"]"));
					ageInput.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("priorityName_edit")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("priorityName_edit")).click();
						driver.findElement(By.id("priorityName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("priorityName_edit")).sendKeys(update);
					}
					driver.findElement(By.id("updatePriorityBtn")).click();
					System.out.println("Data updated successfully for Welcome!");
					break; // Exit after updating the row
				}
			} 
			if (!isFound) {
                System.out.println("Value '" + search + "' not found in the table.");
            }

		} catch (Exception e) {
			
			 e.printStackTrace();
		}
	}
	
	public void searchWithDeleteRecordFromTable(String search) throws InterruptedException {
		
		WebElement sidebar = driver.findElement(By.id("sidebar")); // Replace with actual locator
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", sidebar);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(defectPriority)).click();
	
		WebElement searchValue=driver.findElement(By.xpath("//input[@type=\"search\"]"));
		searchValue.sendKeys(search);
		Thread.sleep(5000);
		try {
			
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id=\"prioritiesTable\"]")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			boolean isFound = false;

			for (int i = 1; i <rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();
				Thread.sleep(5000);
				if (name.equals(search)) {
					// Update the input field value
					WebElement ageInput =cells.get(2).findElement(By.xpath(".//button[text()=\" Delete\"]"));
					ageInput.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deletePriorityBtn")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("deletePriorityBtn")).click();
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
		defectPriority.click();
		add.click();
		driver.getWindowHandle();
		saveBtn.click();
		closeBtn.click();
		String msg = toastmsg.getText();
		System.out.println(msg);
		return msg;

	}

	public void editDefectPriorityName() throws InterruptedException {
		Thread.sleep(5000);
		defectPriority.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='prioritiesTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i <rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals("Hi")) {
					// Update the input field value
					WebElement ageInput =cells.get(2).findElement(By.xpath(".//button[text()=\" Edit\"]"));
					ageInput.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("priorityName_edit")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("priorityName_edit")).click();
						driver.findElement(By.id("priorityName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("priorityName_edit")).sendKeys("Low");
					}
					driver.findElement(By.id("updatePriorityBtn")).click();
					System.out.println("Data updated successfully for Welcome!");
					break; // Exit after updating the row
				}
			}

		} catch (Exception e) {
			
			 e.printStackTrace();
		}
	}
	
	public void deleteRecordFromTable() throws InterruptedException {
		
		defectPriority.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id=\"prioritiesTable\"]")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i <rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();
				Thread.sleep(5000);
				if (name.equals("Welcome")) {
					// Update the input field value
					WebElement ageInput =cells.get(2).findElement(By.xpath(".//button[text()=\" Delete\"]"));
					ageInput.click();
				
					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deletePriorityBtn")).isDisplayed();
					if(cname==true) {
						driver.findElement(By.id("deletePriorityBtn")).click();
					}
					Thread.sleep(5000);
					System.out.println("Data updated successfully for Welcome!");
				}
				i++;
				break; // Exit after updating the row
			}

		} catch (Exception e) {
			
			 e.printStackTrace();
		}
	}
	
	public void specialCharacterNotAllowed(String addPriority1) throws InterruptedException {
		Thread.sleep(3000);
		defectPriority.click();

		add.click();
		driver.getWindowHandle();
		Thread.sleep(3000);
		priorityName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Special characters are not allowed!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
		
	}
	
	public void numericValueNotAllowed(String addPriority1) throws InterruptedException {
		Thread.sleep(5000);
		defectPriority.click();
		
		add.click();
		driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		priorityName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Numbers not allowed in the start!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
	}
	
	public void alphabitWithSpecialCharacterNotAllowed(String addPriority1) throws InterruptedException {
			
		Thread.sleep(5000);
		defectPriority.click();

		add.click();
		driver.getWindowHandle();
		priorityName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Special characters are not allowed!", ActualResult, "Expected result not met");
		}
	
	public void blankValidation(String addPriority1) throws InterruptedException {
		Thread.sleep(5000);
		defectPriority.click();
		
		add.click();
		driver.getWindowHandle();
		priorityName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Please enter a name for priority!", ActualResult, "Expected result not met");
		}
	
	public void ExceedingLimitVerification(String addPriority1) throws InterruptedException {
		Thread.sleep(2000);
		defectPriority.click();
		
		add.click();
		driver.getWindowHandle();
		priorityName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		Thread.sleep(1000);
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Priority name must be within 30 characters!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
		}
	
	public void leadingSpaceVerification(String addPriority1) throws InterruptedException {
		Thread.sleep(5000);
		defectPriority.click();
		
		add.click();
		driver.getWindowHandle();
		priorityName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Please enter a name for priority!", ActualResult, "Expected result not met");
		Thread.sleep(3000);	
		}
}
