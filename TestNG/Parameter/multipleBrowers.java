package Parameter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class multipleBrowers {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String userName = "selenium_11_01@gmail.com";
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if (browserName.equalsIgnoreCase("Firefox")) { //equalsIngoreCase: không phân biệt chữ hoa chữ thường
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		else if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} 
		else if (browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			throw new RuntimeException("Browser name is not valid.");
		}
		
		/*switch (browserName) {
		case "Firefox": {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new EdgeDriver();
			break;
		}
		case "Chrome": {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chrome.exe");
			driver = new EdgeDriver();
			break;
		}
		case "Edge": {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		}
		default:
			throw new RuntimeException("Browser name is not valid.");
		}*/
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	


	@Test
	public void TC_01_LoginToSystem()  {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
