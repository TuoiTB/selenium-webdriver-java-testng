package webdriver;
import java.awt.Checkbox;
import java.io.File;
import java.sql.Driver;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Fluent_Wait {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	FluentWait<WebDriver> fluentWait;
	FluentWait<WebElement> fluentElementWait;
	FluentWait<String> fluentStringWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		// Khởi tạo driver
		driver = new ChromeDriver();
		
		System.out.println(driver.toString());
		
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		
		//driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Fluent_Wait() {
		
		fluentWait = new FluentWait<WebDriver>(driver);
		//fluentElementWait = new FluentWait<WebElement>(driver.findElement(By.xpath(""))) ;
		//fluentStringWait = new FluentWait<String>("");
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start button")).click();
		
		//Cách 1:
		//set timeout tổng thời gian bằng bao nhiêu
		fluentWait.withTimeout(Duration.ofSeconds(5))
		
		//Polling/Interval time: thời gian lặp lại
		.pollingEvery(Duration.ofMillis(500))
		
		//Ignore exception nếu không tìm thấy element
		.ignoring(NoSuchElementException.class);
		
		
		
		//Điều kiện để wait
		//Wait cho element có locator này isDisplayed()
		// //div[@id='finish']/h4[text()='Hello World!']
		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
			}
		});
		Assert.assertTrue(textStatus);
		
		
		String helloText = fluentWait.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
			}
		});
		Assert.assertEquals(helloText, "Hello World!");
		
		
	}

	@Test
	public void TC_02_Fluent_Wait_2() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(12))
		.pollingEvery(Duration.ofMillis(500))
		.ignoring(NoSuchElementException.class);
		
//		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {
//
//			@Override
//			public Boolean apply(WebDriver driver) {
//				// TODO Auto-generated method stub
//				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed();
//			}
//		});
//		Assert.assertTrue(textStatus);
		
		
		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				String text = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText();
				System.out.println(text);
				return text.equals("01:01:00");
			}
		});
		Assert.assertTrue(textStatus);
	}
	
	

	private String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}