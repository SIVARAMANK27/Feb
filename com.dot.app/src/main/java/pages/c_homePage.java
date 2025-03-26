package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class c_homePage {

	private WebDriver driver;

    @FindBy(xpath = "(//a[@class=\"nav-link menu-link collapsed\"])[1]")
    private WebElement Projects;

    @FindBy(xpath="//h1[text()=\"Projects\"]")
    private WebElement title;
   
    public c_homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getWelcomeMessage() {
         Projects.click();
    }
    
    public String getTitleMessage() {
    	return title.getText();
    }
}
