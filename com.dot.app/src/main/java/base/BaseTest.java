package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utils.ExtentReportManager;

public class BaseTest {

	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(BaseTest.class); // Initialize Log4j2 logger
	
	 @BeforeSuite
	    public void initSuite() {
	        ExtentReportManager.initReport();
	    }

	@BeforeMethod
	public void setUp(ITestContext context) {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Git\\Feb\\com.dot.app\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://106.51.127.87:3030/dot/index.html");
		System.out.println("successfully launched chrome driver");
		
		 // Set driver in TestNG context for use in listeners
        context.setAttribute("driver", driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Successfully driver gets closed");
		}
	}
	 @AfterSuite
	 public void flushReport() {
	   ExtentReportManager.flushReport();
	  }
}
