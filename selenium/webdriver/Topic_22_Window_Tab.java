package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Checkbox;
import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.Action;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Window_Tab {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		// Khởi tạo driver
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		System.out.println(driver.toString());
		jsExecutor = (JavascriptExecutor) driver;
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}
	
	//@Test
	public void TC_01_1Github_switchTo_Google() {
		//Trang cha
		driver.get("https://automationfc.github.io/basic-form/index.html");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		//ID của trang hiện tại mà driver đang đứng
		String githubID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		//2 tab = 2 ID
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(githubID)) {
				driver.switchTo().window(id);
				break;
			}
		}
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		
		//Driver đang đứng tại trang google
		String googleID = driver.getWindowHandle();
		for (String id : allIDs) {
			if(!id.equals(googleID)) {
				driver.switchTo().window(id);
				break;
			}
		}
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowById(githubID);
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Selenium java");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
		
	}
	

	
	//@Test
	public void TC_01_2_Github_switchTo_Facebook() {
		//Trang cha
		driver.get("https://automationfc.github.io/basic-form/index.html");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		String githubID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		
		switchToWindowById(githubID);
		
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Facebook - Log in or sign up");
		
		String facebookID = driver.getWindowHandle();
		
		//Switch về trang github
		switchToWindowById(facebookID);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchToWindowById(githubID);
		Assert.assertEquals(driver.getTitle(), "");
		
	}
	
	//@Test
	public void TC_01_3_Complete_testcase_01(){
		//Dùng hàm switchToWindowByTitle
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByTitle("Google");
		sleepInSecond(2);
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Selenium java");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
		sleepInSecond(2);
		
		switchToWindowByTitle("Selenium WebDriver");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(6);
		System.out.println(driver.getTitle());
		driver.findElement(By.id("email")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		sleepInSecond(2);
		
		switchToWindowByTitle("Selenium WebDriver");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(2);
		
		switchToWindowByTitle("Selenium WebDriver");
		sleepInSecond(2);
		
		//Dùng hàm closeAllWindowWithoutExpectedID
		String githubId = driver.getWindowHandle();
		closeAllWindowWithoutExpectedID(githubId);
		
		
	}
	
	//@Test
	public void TC_02_Kyna_English() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		
		//Scroll xuống cuối trang 
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);
		
		switchToWindowByTitle("Kyna.vn - Home | Facebook");
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Kyna.vn - YouTube");
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		sleepInSecond(2);
		
		closeAllWindowWithoutExpectedID(parentID);
		sleepInSecond(2);
		
	}
	
	@Test
	public void TC_03_Live_Tech_Panda() {
		driver.get("http://live.techpanda.org/");
		String parentWindowID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(2);
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		closeAllWindowWithoutExpectedID(parentWindowID);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("success-msg span")).getText(), "The comparison list was cleared.");
		
	}
	
	//Chỉ dùng cho duy nhất 2 window/tab
	public void switchToWindowById(String windowID) {
		//windowID = driver.getWindowHandle(); => Id của window mà driver đang đứng
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
			}
		}
	}
	
	//Có thể chạy cho 2 hoặc nhiều hơn 2 window/tab
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	
	//Hàm close hết các window/tab ngoại trừ window/tab cha ban đầu
	public void closeAllWindowWithoutExpectedID(String expectedID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs)
		{
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
	}
	public void sleepInSecond(long timeout) {
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