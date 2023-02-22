package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_08_Web_Browser_And_Element_Commands {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Verify_URL() {
		//Step 01: Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		//Step 02: Click MY ACCOUNT link tại footer
		//Step 03: Verify URL của login page ="http://live.techpanda.org/index.php/customer/account/login/"
		//Step 04: Click CREATE AN ACCOUNT button
		//Step 05: Verify URL của Register Page = "http://live.techpanda.org/customer/account/create/"
		
		
		
	}

	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
