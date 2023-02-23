package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Exercises {
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
	public void TC_01_Displayed() {
		//Step 1: Truy cập vào trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2: Kiểm tra các phần tử sau hiển thị trên trang: email, age(under 18), education
		if(driver.findElement(By.id("mail")).isDisplayed())
		{
			System.out.println("Email is displayed");
		}	else
		{
			System.out.println("Email is not displayed");
		}
		
		if(driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed())
		{
			System.out.println("Age (under 18) is displayed");
		}	else
		{
			System.out.println("Age (under 18) is not displayed");
		}
		
		if(driver.findElement(By.id("edu")).isDisplayed())
		{
			System.out.println("Education is displayed");
		}	else
		{
			System.out.println("Education is not displayed");
		}
		

		//Step 3: Kiểm tra phần tử sau không hiển thị trên trang: Name:User 5
		if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed())
		{
			System.out.println("Name user 5 is Displayed");
		}	else
		{
			System.out.println("Name user 5 is not Displayed");
		}
		
		
	}

	@Test
	public void TC_02_Enabled() {
		
	}
	
	@Test
	public void TC_03_Selected() {
		
	}
	
	@Test
	public void TC_04_MailChimp() {
		
	}
	
	
	private void sleepInSecond( long timeout) {
		try {
			Thread.sleep(timeout *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
