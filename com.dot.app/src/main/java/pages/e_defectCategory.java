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

public class e_defectCategory {

	public WebDriver driver;

	public e_defectCategory(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"Defect Category\"]")
	WebElement defectCategory;

	@FindBy(xpath = "//button[text()=\" Add\"]")
	WebElement add;

	@FindBy(id = "categoryName")
	WebElement categoryName;

	@FindBy(id = "addCategoryBtn")
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

	public void addDefectCategotyName(String cname) {
		try {
			defectCategory.click();
			add.click();
			driver.getWindowHandle();
			categoryName.sendKeys(cname);
			saveBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String blankValidation_popup() throws InterruptedException {
		Thread.sleep(5000);
		defectCategory.click();
		add.click();
		driver.getWindowHandle();
		saveBtn.click();
		closeBtn.click();
		String msg = toastmsg.getText();
		System.out.println(msg);
		return msg;

	}

	public void editCategoryName() throws InterruptedException {
		Thread.sleep(5000);
		defectCategory.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='categoryTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals("High")) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath(".//button[text()=\" Edit\"]"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("categoryName_edit")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("categoryName_edit")).click();
						driver.findElement(By.id("categoryName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("categoryName_edit")).sendKeys("Low");
					}
					driver.findElement(By.id("updateCategoryBtn")).click();
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
		defectCategory.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='categoryTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals("Low")) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath(".//button[text()=\" Delete\"]"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deleteCategoryBtn")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("deleteCategoryBtn")).click();
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

	public void searchWithTableDeleteRecord(String search) throws InterruptedException {
		Thread.sleep(5000);
		defectCategory.click();

		WebElement searchValue = driver.findElement(By.xpath("//input[@type=\"search\"]"));
		searchValue.sendKeys(search);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='categoryTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			boolean isFound = false;

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals(search)) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath(".//button[text()=\" Delete\"]"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deleteCategoryBtn")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("deleteCategoryBtn")).click();
					}
					Thread.sleep(5000);
					System.out.println("Data deleted successfully!");
					break; // Exit after updating the row
				}
			}
			if (!isFound) {
				System.out.println("Value '" + searchValue + "' not found in the table.");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void searchEditRecordFromTable(String search, String update) throws InterruptedException {

		Thread.sleep(5000);
		defectCategory.click();
		driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(search);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='categoryTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			boolean isFound = false;

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals(search)) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath(".//button[text()=\" Edit\"]"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("categoryName_edit")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("categoryName_edit")).click();
						driver.findElement(By.id("categoryName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("categoryName_edit")).sendKeys(update);
					}
					driver.findElement(By.id("updateCategoryBtn")).click();
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
	
	public void specialCharacterNotAllowed(String addPriority1) throws InterruptedException {
		Thread.sleep(3000);
		defectCategory.click();

		add.click();
		driver.getWindowHandle();
		Thread.sleep(3000);
		categoryName.sendKeys(addPriority1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Special characters are not allowed!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
		
	}
	
	public void numericValueNotAllowed(String CategoryName1) throws InterruptedException {
		Thread.sleep(5000);
		defectCategory.click();
		
		add.click();
		driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		categoryName.sendKeys(CategoryName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Numbers not allowed in the start!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
	}
	
	public void alphabitWithSpecialCharacterNotAllowed(String CategoryName1) throws InterruptedException {
			
		Thread.sleep(5000);
		defectCategory.click();

		add.click();
		driver.getWindowHandle();
		categoryName.sendKeys(CategoryName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Special characters are not allowed!", ActualResult, "Expected result not met");
		}
	
	public void blankValidation(String CategoryName1) throws InterruptedException {
		Thread.sleep(5000);
		defectCategory.click();
		
		add.click();
		driver.getWindowHandle();
		categoryName.sendKeys(CategoryName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Please enter a name for priority!", ActualResult, "Expected result not met");
		}
	
	public void ExceedingLimitVerification(String CategoryName1) throws InterruptedException {
		Thread.sleep(2000);
		defectCategory.click();
		
		add.click();
		driver.getWindowHandle();
		Thread.sleep(3000);
		categoryName.sendKeys(CategoryName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Priority name must be within 30 characters!", ActualResult, "Expected result not met");
		Thread.sleep(3000);
		}
	
	public void leadingSpaceVerification(String CategoryName1) throws InterruptedException {
		Thread.sleep(3000);
		defectCategory.click();
		
		add.click();
		driver.getWindowHandle();
		Thread.sleep(3000);
		categoryName.sendKeys(CategoryName1);
		saveBtn.click();
		cancelBtn.click();
		String ActualResult=driver.findElement(By.xpath("//*[@id=\"toastMessage\"]")).getText();
		System.out.println(ActualResult);
		Assert.assertEquals("Please enter a name for priority!", ActualResult, "Expected result not met");
		Thread.sleep(3000);	
		}
}