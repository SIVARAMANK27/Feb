package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class j_defectPage {
	
	public WebDriver driver;

	@FindBy(xpath="//span[text()=\"Defects\"]")
	WebElement defect;
	
	@FindBy(id="addBtn")
	WebElement addBtn;
	
	@FindBy(id="defectDescription")
	WebElement defectDescription;
	
	@FindBy(id="stepsToReproduce")
	WebElement stepsToReproduce;
	
	@FindBy(id="defectCategory")
	WebElement defectCategory;
	
	@FindBy(id="defectStatus")
	WebElement defectStatus;
	
	@FindBy(id="defectPriority")
	WebElement defectPriority;
	
	@FindBy(id="defectAssignee")
	WebElement assigne;
	
	@FindBy(id="targetDate")
	WebElement closureDate;
	
	@FindBy(id="addDefectBtn")
	WebElement saveBtn;
	
	@FindBy(id="projectList")
	WebElement projectList;
	
	@FindBy(id="defectsList")
	WebElement defectsList;
	
	@FindBy(xpath="//input[@id=\"search\"]")
	WebElement search;
	
	@FindBy(id="editDefectBtn")
	WebElement editDefectBtn;
	
	@FindBy(id="editDefectPriority")
	WebElement editDefectPriority;
	
	public j_defectPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addDefect(String description, String Steps, String category, String status, String priority, String a, String closeDate) throws InterruptedException {
		Thread.sleep(5000);
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(3000);
		defectDescription.sendKeys(description);
		Thread.sleep(3000);
		stepsToReproduce.sendKeys(Steps);
		Actions action=new Actions(driver);
		action.moveToElement(defectCategory).click().perform();
		
		Select select=new Select(defectCategory);
		select.selectByVisibleText(category);
		
		Select select1=new Select(defectStatus);
		select1.selectByVisibleText(status);
		
		Select select2=new Select(defectPriority);
		select2.selectByVisibleText(priority);
		
		Select select3=new Select(assigne);
		String numStr=a;
		int number=Integer.parseInt(numStr);
		select3.selectByIndex(number);
		
		closureDate.sendKeys(closeDate);
		saveBtn.click();
		
		System.out.println("successfully aaded the defect");
	}
	
	public void searchWithEditText(String search1, String closureDate, String defectPriority) throws InterruptedException {
		defect.click();
		Select select=new Select(projectList);
		select.selectByIndex(1);
		search.sendKeys(search1);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@title=\"Edit\"]")).click();
		Thread.sleep(3000);		
		
		Select select1=new Select(editDefectPriority);
		select1.selectByVisibleText(defectPriority);
		editDefectBtn.click();
		Thread.sleep(3000);
		System.out.println("Successfully updated the application");
	}
	
	public void searchWithDelete(String search1) throws InterruptedException{
		defect.click();
		Select select=new Select(projectList);
		select.selectByIndex(1);
		search.sendKeys(search1);
		Thread.sleep(3000);
		driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@title=\"Delete\"]")).click();
		driver.getWindowHandle();
		driver.findElement(By.id("deleteDefectBtn")).click();
		Thread.sleep(3000);
		System.out.println("Successfully deleted the application");
	}
	
	public void emptyStepToReproduce(String description) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please enter the steps to reproduce!", "Text is mismatch");
	}
	
	public void emptyClosureDateField(String description, String steps) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the target date!", "Text is mismatch");
	}
	
	public void emptyCategoryNameField(String description, String steps, String ClosureDate) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the category!", "Text is mismatch");
	}
	
	public void checkTextAvailableCategoryNameField(String description, String steps, String ClosureDate, String dorpDownValue) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		
		Select select=new Select(defectCategory);
		List<WebElement> options = select.getOptions();
		 if (options.size() > 0) {
	            System.out.println("Dropdown has values.");
	            for (WebElement option : options) {
	                System.out.println("Option: " + option.getText());
	            }
	        } else {
	            System.out.println("Dropdown is empty.");
	        }
		 
		 boolean found = false;

	        for (WebElement option : options) {
	            if (option.getText().equalsIgnoreCase(dorpDownValue)) {
	                found = true;
	                break;
	            }
	        }

	        if (found) {
	            System.out.println("Dropdown contains the text: " + dorpDownValue);
	        } else {
	            System.out.println("Dropdown does NOT contain the text: " + dorpDownValue);
	        }
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the category!", "Text is mismatch");
	}
	
	public void emptyStatusField(String description, String steps, String ClosureDate) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		Select select5=new Select(defectCategory);
		select5.selectByIndex(1);
	
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the status!", "Text is mismatch");
	}
	
	public void checkTextAvailableStatusNameField(String description, String steps, String ClosureDate, String dorpDownValue) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		Select select5=new Select(defectCategory);
		select5.selectByIndex(1);
		
		Select select=new Select(defectStatus);
		List<WebElement> options = select.getOptions();
		 if (options.size() > 0) {
	            System.out.println("Dropdown has values.");
	            for (WebElement option : options) {
	                System.out.println("Option: " + option.getText());
	            }
	        } else {
	            System.out.println("Dropdown is empty.");
	        }
		 
		 boolean found = false;

	        for (WebElement option : options) {
	            if (option.getText().equalsIgnoreCase(dorpDownValue)) {
	                found = true;
	                break;
	            }
	        }

	        if (found) {
	            System.out.println("Dropdown contains the text: " + dorpDownValue);
	        } else {
	            System.out.println("Dropdown does NOT contain the text: " + dorpDownValue);
	        }
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the status!", "Text is mismatch");
	}
	
	
	public void emptyProrityTypeField(String description, String steps, String ClosureDate) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		Select select5=new Select(defectCategory);
		select5.selectByIndex(1);
		Thread.sleep(3000);
		
		Select select6=new Select(defectStatus);
		select6.selectByIndex(1);
		
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the priority!", "Text is mismatch");
	}
	
	public void checkTextAvailablePriority(String description, String steps, String ClosureDate, String dorpDownValue) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		Select select5=new Select(defectCategory);
		select5.selectByIndex(1);
		
		Thread.sleep(3000);
		Select select6=new Select(defectStatus);
		select6.selectByIndex(1);
		
		Select select=new Select(defectPriority);
		List<WebElement> options = select.getOptions();
		 if (options.size() > 0) {
	            System.out.println("Dropdown has values.");
	            for (WebElement option : options) {
	                System.out.println("Option: " + option.getText());
	            }
	        } else {
	            System.out.println("Dropdown is empty.");
	        }
		 
		 boolean found = false;

	        for (WebElement option : options) {
	            if (option.getText().equalsIgnoreCase(dorpDownValue)) {
	                found = true;
	                break;
	            }
	        }

	        if (found) {
	            System.out.println("Dropdown contains the text: " + dorpDownValue);
	        } else {
	            System.out.println("Dropdown does NOT contain the text: " + dorpDownValue);
	        }
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the priority!", "Text is mismatch");
	}
	
	
	public void emptyAssigneField(String description, String steps, String ClosureDate) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		Select select5=new Select(defectCategory);
		select5.selectByIndex(1);
		Thread.sleep(3000);
		
		Select select6=new Select(defectStatus);
		select6.selectByIndex(1);
		
		Select select7=new Select(defectPriority);
		select7.selectByIndex(2);
		
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the assigne!", "Text is mismatch");
	}
	
	public void checkTextAvailableAssigne(String description, String steps, String ClosureDate, String dorpDownValue) throws InterruptedException {
		defect.click();
		Select select4=new Select(projectList);
		select4.selectByIndex(1);
	
		addBtn.click();
		Thread.sleep(5000);
		defectDescription.sendKeys(description);
		stepsToReproduce.sendKeys(steps);
		closureDate.sendKeys(ClosureDate);
		Select select5=new Select(defectCategory);
		select5.selectByIndex(1);
		
		Thread.sleep(3000);
		Select select6=new Select(defectStatus);
		select6.selectByIndex(1);
		
		Select select7=new Select(defectPriority);
		select7.selectByIndex(1);
		
		Select select=new Select(assigne);
		List<WebElement> options = select.getOptions();
		 if (options.size() > 0) {
	            System.out.println("Dropdown has values.");
	            for (WebElement option : options) {
	                System.out.println("Option: " + option.getText());
	            }
	        } else {
	            System.out.println("Dropdown is empty.");
	        }
		 
		 boolean found = false;

	        for (WebElement option : options) {
	            if (option.getText().equalsIgnoreCase(dorpDownValue)) {
	                found = true;
	                break;
	            }
	        }

	        if (found) {
	            System.out.println("Dropdown contains the text: " + dorpDownValue);
	        } else {
	            System.out.println("Dropdown does NOT contain the text: " + dorpDownValue);
	        }
	        
		saveBtn.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		Assert.assertEquals(expectedResult, "Please select the assignee!", "Text is mismatch");
	}
}
