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

public class i_projectsPage {

	public WebDriver driver;

	public i_projectsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()=\"Projects\"]")
	WebElement Projects;

	@FindBy(xpath = "//button[text()=\" Add\"]")
	WebElement add;

	@FindBy(id = "projectName")
	WebElement projectName;
	
	@FindBy(id = "projectName_edit")
	WebElement projectName_edit;
	
	@FindBy(id = "description")
	WebElement description;
	
	@FindBy(id = "description_edit")
	WebElement description_edit;
	
	@FindBy(id = "projectPhase")
	WebElement projectPhase;
	
	@FindBy(id = "projectPhase_edit")
	WebElement projectPhase_edit;
	
	@FindBy(id = "projectStatus")
	WebElement projectStatus;
	
	@FindBy(id = "projectStatus_edit")
	WebElement projectStatus_edit;
	
	@FindBy(id = "addProjectBtn")
	WebElement saveBtn;
	
	@FindBy(id = "updateProjectBtn")
	WebElement updateBtn;

	@FindBy(xpath = "(//button[text()=\" Cancel\"])[1]")
	WebElement cancelBtn;

	@FindBy(id = "startDate")
	WebElement startDate;

	@FindBy(id = "endDate")
	WebElement endDate;	
	
	@FindBy(id="endDate_edit")
	WebElement endDate_edit;
	
	@FindBy(xpath="//input[@type=\"search\"]")
	WebElement search;
	
	public void addProjectDetails(String projects, String descriptions, String startDate1, String EndDate1) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		Thread.sleep(3000);
		description.sendKeys(descriptions);
		
		Select select=new Select(projectPhase);
		select.selectByIndex(1);
		
		Select select1=new Select(projectStatus);
		select1.selectByIndex(1);
		
		startDate.sendKeys(startDate1);
		endDate.sendKeys(EndDate1);
		saveBtn.click();
		
	}
	
	public void editProjectDetails(String searchText, String update, String updateDate) throws InterruptedException {
		
		Projects.click();
	    search.sendKeys(searchText);
	    Thread.sleep(5000);
		try {
			WebDriverWait wait=new WebDriverWait(driver,10);
			WebElement table =	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='projectTable']")));
		
			List<WebElement>rows=table.findElements(By.tagName("tr"));
			for(int i=1; i<rows.size();i++) {
				List<WebElement>cells=rows.get(i).findElements(By.tagName("td"));
				String name = cells.get(1).getText();
					
				Thread.sleep(5000);
				if(name.equalsIgnoreCase(searchText)) {
					WebElement ageInput = cells.get(2).findElement(By.xpath("//button[@title=\"Edit\"]"));
					ageInput.click();
					boolean project=projectName_edit.isDisplayed();
					if(project==true) {
						projectName_edit.clear();
						Thread.sleep(3000);
						projectName_edit.sendKeys(update);	
						endDate_edit.sendKeys(updateDate);
					}
					updateBtn.click();
					System.out.println("Updated successfully");
					break;
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProjectRecord(String searchText) throws InterruptedException {
		Thread.sleep(5000);
		Projects.click();
		search.sendKeys(searchText);
		
		try {
			WebDriverWait wait=new WebDriverWait(driver,10);
			WebElement table=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("projectTable")));
			List<WebElement>rows=table.findElements(By.tagName("tr"));
			
			for(int i=1; i<rows.size();i++) {
				List<WebElement>cells=rows.get(i).findElements(By.tagName("td"));
				String name = cells.get(1).getText();
								
				if(name.equalsIgnoreCase(searchText)) {
					WebElement ageInput = cells.get(2).findElement(By.xpath("//button[@title=\"Delete\"]"));
					ageInput.click();
					driver.getWindowHandle();
					driver.findElement(By.id("deleteProjectBtn")).click();
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
public void emptyValidationForProjectName(String projects) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		
		saveBtn.click();
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		String expectedResult=errorMsg.getText();
		
		Assert.assertEquals("Please enter a name for project!", expectedResult, "Text mismatch");
	}
	
	public void spcialCharacterNotAllowed(String projects) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		
		saveBtn.click();	
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Special characters are not allowed!", expectedResult, "Text mismatch");
	}
	
	public void exceedingLimitVerification(String projects) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		
		saveBtn.click();	
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Project name must be within 30 characters!", expectedResult, "Text mismatch");
	}
	
	public void leadingSpaceVerificcation(String projects) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		
		saveBtn.click();	
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please enter a name for project!", expectedResult, "Text mismatch");
	}
	
	public void leadingSpaceVerificcation1(String projects, String description1) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		description.sendKeys(description1);		
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please enter a description!", expectedResult, "Text mismatch");
	}
	
	public void exceedingLimitVerification1(String projects, String description1) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
	
		description.sendKeys(description1);		
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Description must be within 255 characters!", expectedResult, "Text mismatch");
	}
	
	public void emptyValidationForProjectName1(String projects, String description1) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
	
		description.sendKeys(description1);		
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please enter a description!", expectedResult, "Text mismatch");
	}
	
	public void verifyValidTextAreAvailable(String projects, String description1, String dorpDownValue) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
	
		description.sendKeys(description1);		
		Select select=new Select(projectPhase);
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
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please select a phase for project!", expectedResult, "Text mismatch");
	}
	
	public void dontSelectProjectPhase(String projects, String description1) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
	
		description.sendKeys(description1);		
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please select a phase for project!", expectedResult, "Text mismatch");
	}
	
	
	public void verifyValidTextStatus(String projects, String description1, String dorpDownValue, String DropDownStatus) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
	
		description.sendKeys(description1);		
		Select select=new Select(projectPhase);
		select.selectByIndex(1);
		/*List<WebElement> options = select.getOptions();
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
	        }*/
	        
	        
	        Select select1=new Select(projectStatus);
			List<WebElement> options = select1.getOptions();
			 if (options.size() > 0) {
		            System.out.println("Dropdown has values.");
		            for (WebElement option : options) {
		                System.out.println("Option: " + option.getText());
		            }
		        } else {
		            System.out.println("Dropdown is empty.");
		        }
			
			 
			 boolean found1 = false;

		        for (WebElement option : options) {
		            if (option.getText().equalsIgnoreCase(DropDownStatus)) {
		                found1 = true;
		                break;
		            }
		        }

		        if (found1) {
		            System.out.println("Dropdown contains the text: " + DropDownStatus);
		        } else {
		            System.out.println("Dropdown does NOT contain the text: " + DropDownStatus);
		        }
	        
	        
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please select a status for project!", expectedResult, "Text mismatch");
	}
	
	public void dontSelectStatus(String projects, String description1, String DropdownPhase) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
	
		description.sendKeys(description1);	
			
		Select select=new Select(projectPhase);
		select.selectByIndex(1);
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Please select a status for project!", expectedResult, "Text mismatch");
	}
	
  public void DateValidation(String projects, String description1, String sdate, String edate) throws InterruptedException {
		
		Projects.click();
		add.click();
		Thread.sleep(3000);
		projectName.sendKeys(projects);
		description.sendKeys(description1);		
		
		Select select=new Select(projectPhase);
		select.selectByIndex(1);
			        
		Select select1=new Select(projectStatus);
		select1.selectByIndex(1);
		
		startDate.sendKeys(sdate);
		endDate.sendKeys(edate);
		Thread.sleep(5000);
		saveBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toastMessage\"]")));
		Assert.assertTrue(errorMsg.isDisplayed());
		
		String expectedResult=errorMsg.getText();
		
		System.out.println(expectedResult);
		Assert.assertEquals("Project already exists.", expectedResult, "Text mismatch");
	}
}

