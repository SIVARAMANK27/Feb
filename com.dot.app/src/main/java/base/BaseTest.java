package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ExtentReportManager;

public class BaseTest {

	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(BaseTest.class); // Initialize Log4j2 logger

	@BeforeMethod
	public void setUp(ITestContext context) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\eclipse-workspace\\com.dot.app\\driver\\chromedriver.exe");
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
	ExtentReportManager.flushReport();
	}
}
