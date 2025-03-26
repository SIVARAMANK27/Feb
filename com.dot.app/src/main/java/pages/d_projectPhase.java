package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class d_projectPhase {

	public WebDriver driver;
	
	@FindBy(xpath="//span[text()=\"Project Phase\"]")
	WebElement projectPhase;
	
	@FindBy(xpath = "//button[text()=\" Add\"]")
	WebElement add;
	
	@FindBy(xpath="//input[@id=\"phaseName\"]")
	WebElement phaseName;
	
	@FindBy(id = "addPhaseBtn")
	WebElement saveBtn;
	
	@FindBy(xpath = "(//button[text()=\" Cancel\"])[1]")
	WebElement cancelBtn;
	
	@FindBy(xpath = "//div[text()=\"Please enter a name for phase!\"]")
	WebElement toastmsg;
	
	@FindBy(xpath = "(//button[@aria-label=\"Close\"])[2]")
	WebElement closeBtn;

	@FindBy(xpath = "//button[text()=\" Edit\"]")
	WebElement edit;

	@FindBy(xpath = "(//button[text()=\" Delete\"])[1]")
	WebElement delete;
	
	public d_projectPhase(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addProjectPhaseName(String name) {
		try {
			projectPhase.click();
			add.click();
			driver.getWindowHandle();
			phaseName.sendKeys(name);
			saveBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String blankValidation_popup() throws InterruptedException {
		Thread.sleep(5000);
		projectPhase.click();
		add.click();
		driver.getWindowHandle();
		saveBtn.click();
		closeBtn.click();
		String msg = toastmsg.getText();
		System.out.println(msg);
		return msg;

	}

	public void editProjectPhaseName() throws InterruptedException {
		Thread.sleep(5000);
		projectPhase.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='phaseTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals("Development")) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath(".//button[text()=\" Edit\"]"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("phaseName_edit")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("phaseName_edit")).click();
						driver.findElement(By.id("phaseName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("phaseName_edit")).sendKeys("MySKM");
					}
					driver.findElement(By.id("updatePhaseBtn")).click();
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
		projectPhase.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='phaseTable']")));

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
					boolean cname = driver.findElement(By.id("deletePhaseBtn")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("deletePhaseBtn")).click();
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

	public void searchWithTableDeleteRecord(String deleteText) throws InterruptedException {
		Thread.sleep(5000);
		projectPhase.click();

		
		WebElement searchValue = driver.findElement(By.xpath("//input[@type=\"search\"]"));
		searchValue.sendKeys(deleteText);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='phaseTable']")));

			// Find all rows in the table
			List<WebElement> rows = table.findElements(By.tagName("tr"));

			boolean isFound = false;

			for (int i = 1; i < rows.size(); i++) { // Start from 1 to skip header
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

				String name = cells.get(1).getText();

				if (name.equals(deleteText)) {
					// Update the input field value
					WebElement ageInput = cells.get(2).findElement(By.xpath(".//button[text()=\" Delete\"]"));
					ageInput.click();

					driver.getWindowHandle();
					// Click the update button
					boolean cname = driver.findElement(By.id("deletePhaseBtn")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("deletePhaseBtn")).click();
					}
					Thread.sleep(5000);
					System.out.println("Data updated successfully for Welcome!");
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

	public void searchEditRecordFromTable(String searchText, String updateText) throws InterruptedException {

		Thread.sleep(5000);
		projectPhase.click();
		String search = searchText;
		driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys(search);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement table = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='phaseTable']")));

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
					boolean cname = driver.findElement(By.id("phaseName_edit")).isDisplayed();
					if (cname == true) {
						driver.findElement(By.id("phaseName_edit")).click();
						driver.findElement(By.id("phaseName_edit")).clear();
						Thread.sleep(5000);
						driver.findElement(By.id("phaseName_edit")).sendKeys(updateText);
					}
					driver.findElement(By.id("updatePhaseBtn")).click();
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
}
