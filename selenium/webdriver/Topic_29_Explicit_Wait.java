package webdriver;

import java.awt.Checkbox;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_29_Explicit_Wait {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
		explicitWait = new WebDriverWait(driver, 15);
		System.out.println(driver.toString());
		
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_() {
		driver.get("https://www.facebook.com/");
		
		//Chờ cho 1 alert xuất hiện trong HTML
			//Hàm này vừa chờ và switch vào alert luôn, k cần switchTo vào alert nữa
		Alert newAlert = explicitWait.until(ExpectedConditions.alertIsPresent()); //**
		newAlert.accept();
		newAlert.dismiss();
		
		//Chờ cho 1 attribute  có value
			//Hàm này đượ dùng trước hàm getAttribute()
		explicitWait.until(ExpectedConditions.attributeContains(By.id("login_username"), "placeholder", "số điện thoại"));
		explicitWait.until(ExpectedConditions.attributeToBe(By.id("login_username"), "placeholder", "Nhập số điện thoại hoặc email")); //**
		
		
		//Chờ cho 1 element có thể được click hay không: button/ checkbox/ radio/ link/ 
			//Hàm này được dùng trước hàm click()
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fhs-btn-login"))); //**
		
		
		//Chờ cho 1 element đã được chọn hay chưa, dùng cho checkbox/radio
			//Dùng trước khi apply hàm isSelected()
		explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("input[name='sex']"))); //*
		
		
		//Chờ cho frame xuất hiện và switchTo vào frame đó
			//Hàm này vừa chờ + switchTo vào frame luôn
		WebDriver newFrame = explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(""))); //*
		
		//Chờ cho 1 element không còn visible nữa
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(""))); //*
		
		//Chờ nhiều elements k còn visible 
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));
		
		//Var arguments
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
		
		
		//Chờ cho các element xuất hiện có tổng là bao nhiêu
			//Bằng 3 thì pass
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input[name='sex']"), 3)); //*
			//Ít hơn 3 là pass
		explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("input[name='sex']"), 3));
			//Nhiều hơn 3 là pass
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("input[name='sex']"), 3));
			//Các hàm wait này dùng trước khi lấy ra số lượng element bằng bao nhiêu
		
		//Lấy ra số lượng element bằng bao nhiêu
		int radioNumber = driver.findElements(By.cssSelector("")).size();
		
		//Thao tác và nó bật ra các tab/ window 
		// Chờ cho bao nhiêu cửa sổ/ tab được xuất hiện
		boolean windowActive = explicitWait.until(ExpectedConditions.numberOfWindowsToBe(4));
		
		//Chờ cho element có trong HTML (Không cần quan tâm visible hay không)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='sex']"))); //*
		
		//Chờ cho nhiều elements nó có trong HTML (Không cần quan tâm visible hay không) 
		//thường dùng với dropdown (item)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='sex']")));
		
		WebElement loginUsernameTextbox = explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.popup-login-content"),By.cssSelector("input#login_username")));
		List<WebElement> loginTextbox = explicitWait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.cssSelector("div.popup-login-content"), By.cssSelector("input#login_username")));
		
		//Chờ cho 1 element không còn trong HTML nữa
		boolean notHTML = explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("input[name='sex']"))));
		Assert.assertTrue(notHTML);
		
		//Dùng trước hàm getText()
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.trusted-rp-name"), "Unbuntu One"));  //*
		explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.trusted-rp-name"), "Unbuntu One"));
		explicitWait.until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector("h2.login-title"), "login_title"));
		
		//Dùng trước hàm getTitle()
		explicitWait.until(ExpectedConditions.titleContains("Fahasa.com")); //Tương đối
		explicitWait.until(ExpectedConditions.titleIs("Nhà sách trực tuyến Fahasa.com - FAHASA.COM")); //Tuyệt đối
		
		//Dùng trước hàm getCurrentUrl()
		explicitWait.until(ExpectedConditions.urlContains(""));
		explicitWait.until(ExpectedConditions.urlToBe("https://www.fahasa.com/"));
		
		//Chờ cho 1 element hiển thị
		WebElement element = driver.findElement(By.cssSelector(""));
		explicitWait.until(ExpectedConditions.visibilityOf(element));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))); //**
		
		//Chờ cho nhiều elements được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(""))); //**
		
		
		//Chờ cho 1 element được hiển thị
		//Không dùng Nested trong thực tế nên k viết ở đây (nếu cần dùng thì xem lại video topic 40
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
		driver.quit();
	}
}