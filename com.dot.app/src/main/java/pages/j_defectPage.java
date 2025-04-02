package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	
}
