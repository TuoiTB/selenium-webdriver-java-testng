package webdriver;


import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
//	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		sleepInSecond(2);
		//Switch qua Alert/Frame(iFrame)/window
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		//Alert alert = driver.switchTo().alert(); //=>Không chờ cho alert xuất hiện để switch
		
		//GetText ra để verify title của alert
		alert.getText();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
				
		//Accept alert
		alert.accept();
		
		//Verify sau khi accept alert
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
		//Cancel alert
		//alert.dismiss();
		
		
		
		//Nhập liệu vào alert
		//alert.sendKeys("bjbgjbdjgdjg");
	}
	
	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
	//@Test
	public void TC_03_Prompt_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String senkeyInput = "tubaotuoi";
		alert.sendKeys(senkeyInput);
		sleepInSecond(2);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + senkeyInput);
		
	}
	@Test
	public void TC_04_Authentication_Alert() {
		//Không dùng thư viện alert để xử lý được
		//Dùng selenium trick:
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
	
	}
	@Test
	public void TC_05_Authentication_Alert_AUTO_IT() {
		driver.get("http://the-internet.herokuapp.com/basic_auth");
	
	}
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
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