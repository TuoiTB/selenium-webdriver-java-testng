package Exercise;

import java.awt.Checkbox;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

public class Topic_32_Popup_Part_I {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC_01_Fixed_Popup_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog button.btn-login-v1")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog button.close")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}
	
	//@Test
	public void TC_02_Fixed_Popup_IN_DOM_Kyana() {
		driver.get("https://skills.kynaenglish.vn/");
		driver.findElement(By.cssSelector("a[class='login-btn']")).click();
		sleepInSecond(2);
		
		By loginPopup = By.cssSelector("div[class='k-popup-account-mb-content']");
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div[class='k-popup-account-mb-content'] input[id='user-login']")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("div[class='k-popup-account-mb-content'] input[id='user-password']")).sendKeys("123456");
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("div[class='k-popup-account-mb-content'] button[id='btn-submit-login']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='k-popup-account-mb-content'] div[id='password-form-login-message']")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button[class='k-popup-account-close close']")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
	}
	
	
	//@Test
	public void TC_03_Fixed_Popup_NOT_IN_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		By loginPopup = By.cssSelector("div.styles__Root-sc-2hr4xa-0");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("div.styles__Root-sc-2hr4xa-0 p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
		driver.findElement(By.cssSelector("button.btn-close")).click();
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}
	
	@Test
	public void TC_04_Fixed_Popup_NOT_IN_DOM_FB() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		By loginPopup = By.cssSelector("div._n3");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		
		
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