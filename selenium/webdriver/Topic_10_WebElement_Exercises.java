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
		//Step 01: Truy cập trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 02: Kiểm tra phần tử sau enable trên trang: Email, Age, education, job role 01/ job role 02,interests checkbox, slider 01
		//Email
		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else
		{
			System.out.println("Element is disabled");
		}
		
		//Age
		if(driver.findElement(By.xpath("//label[text()='Under 18']")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else
		{
			System.out.println("Element is disabled");
		}
		
		//Education
		if(driver.findElement(By.id("edu")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else
		{
			System.out.println("Element is disabled");
		}
		
		//Job role 01
		if(driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else
		{
			System.out.println("Element is disabled");
		}
		
		//slider 01
		if(driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else 
		{
			System.out.println("Element is disabled");
		}
		
		//Check box is disabled
		if(driver.findElement(By.xpath("//label[@for='check-disbaled']")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else 
		{
			System.out.println("Element is disabled");
		}
		
		//Slider 02 disabled
		if(driver.findElement(By.id("slider-2")).isEnabled()) {
			System.out.println("Element is enabled");
		}	else 
		{
			System.out.println("Element is disabled");
		}
	}
		
	
	@Test
	public void TC_03_Selected() {
		//Step 01: Truy cập trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 02: Click chọn 
		//Age (under 18) radio button
		driver.findElement(By.xpath("//label[text()='Under 18']")).click();
		//"Languages:Java" checkbox
		driver.findElement(By.xpath("//label[@for='java']")).click();
		sleepInSecond(3);
		
		//Step 03: Kiểm tra các phần tử tại step 2 đã được chọn
		//Hàm assertTrue dùng để kiểm tra 1 element đã được chọn thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Under 18']")).isSelected());
		//Hàm assertFalse dùng để kiểm tra 1 element chưa được chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Under 18']")).isSelected());
		
		//Step 04: Click dể bỏ chọn "Languages:Java" checkbox 
		driver.findElement(By.xpath("//label[text()='Under 18']")).click();
		driver.findElement(By.xpath("//label[@for='java']")).click();
		
		//Step 05: Kiểm tra phần tử "Languages:Java" checkbox đã được bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[@for='java']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Under 18']")).isSelected());
		
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
