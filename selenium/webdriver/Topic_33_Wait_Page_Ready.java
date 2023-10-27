package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_33_Wait_Page_Ready {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Actions action;
	
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
		
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		action = new Actions(driver);
	}
	
	//@Test
	public void TC_01_noCommerce_Case_1() {
		//Step 01: Truy cập vào trang
		driver.get("https://admin-demo.nopcommerce.com");
		
		//Step 02: Login => click vào button Login
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		//Step 03: Cách 1: Wait for ajax busy icon loading invisible
		//assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']/span"))));
		Assert.assertTrue(waitForAjaxIconInvisible());
		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("button.login-button")).isDisplayed());
	
		
		
	}
	
	//@Test
	public void TC_01_noCommerce_Case_2() {
		//Step 01: Truy cập vào trang
		driver.get("https://admin-demo.nopcommerce.com");
				
		//Step 02: Login => click vào button Login
				
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		//Step 03: Cách 2: Wait for page load complete/ page ready
		//Dùng hàm isPageLoadedSuccess
		Assert.assertTrue(isPageLoadedSuccess());
		
		
	}
	
	//@Test
	public void TC_02_Test_Blog() {
		//Cách này vẫn chạy được nhưng tính ổn định k cao
		driver.get("https://blog.testproject.io/");
		
	//	Assert.assertTrue(isPageLoadedSuccess());
		
	//	action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title a"));
		for (WebElement title : postTitles) {
			System.out.println(title.getText());
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
		
	}
	@Test
	public void TC_02_02_Test_Blog() {
		//có wait cho page load xong => tính ổn định cao
		driver.get("https://blog.testproject.io/");
		
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.main-heading")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title a"));
		for (WebElement title : postTitles) {
			System.out.println(title.getText());
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	//viết hàm cho cách 1
	public boolean waitForAjaxIconInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']/span")));
	}
	
	//Step 03: Cách 2: Wait for page load complete/ page ready
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		//Điều kiện 1
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		//Điều kiện 2
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
}