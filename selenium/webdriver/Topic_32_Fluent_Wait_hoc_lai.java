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

public class Topic_32_Fluent_Wait_hoc_lai {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	FluentWait<WebDriver> fluentWait ;
	//FluentWait<WebElement> fluentElementWait;
	//FluentWait<String> fluentStringWait;
	
	
	
	
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
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		
		fluentWait = new FluentWait<WebDriver>(driver);
		//fluentElementWait = new FluentWait<WebElement>(driver.findElement(By.xpath("")));
		//fluentStringWait = new FluentWait<String>("");
		
		//Set timeout tổng time bằng bao nhiêu
		fluentWait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofMillis(200)) //Polling/interval time: lặp lại
		.ignoring(NoSuchElementException.class);		//Ignore exception nếu không tìm thấy element
		
		//Điều kiện để Wait
		//Waitcho element có locator này isDisplayed()
		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				//T là tham số của hàm apply - type của fluent wait khi khởi tạo lại
				//V là kiểu trả về của hàm apply
				
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
			}
		}); 
		Assert.assertTrue(textStatus);
		
		
		
		//Wait cho element có text = Hello Word => getText()
		String helloText = fluentWait.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
			}
		});
		Assert.assertEquals(helloText,"Hello World!");
	}
	
	@Test
	public void TC_02_exercise() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start button")).click();
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		Boolean status = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver testfluentwait) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
			}
		});
		Assert.assertTrue(status);
	}
	
	//@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(12))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		boolean statusTime = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed();
			}
		});
		Assert.assertTrue(statusTime);
	}
	
	//@Test
	public void TC_04() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(500))
		.ignoring(NoSuchElementException.class);
		
		Boolean textTimeStatus = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				String textTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText();
				System.out.println(textTime);
				return textTime.equals("01:01:00");
			}
		});
		Assert.assertTrue(textTimeStatus);
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}