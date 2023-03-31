package webdriver;

import static org.testng.Assert.assertEquals;

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

public class Topic_19_Fixed_Popup {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Fix_Popup_In_HTML() {
		driver.get("https://ngoaingu24h.vn/");
		
		//Luôn có trong html dù popup có hiển thị hay không
		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog");
		
		//Click button login
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		//Verify login popup is displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		//Nhập thông tin username/password = automationfc
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog input#password-input")).sendKeys("automationfc");
		
		//Click button Đăng nhập
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog button.btn-login-v1")).click();
		sleepInSecond(2);
		
		//Verify msg error
		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
		
		//Close popup
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog button.close")).click();
		sleepInSecond(2);
		
		//Verify login popup is NOT displayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
	}
	
	@Test
	public void TC_02_Fixed_Popup_In_DOM() {
		driver.get("https://skills.kynaenglish.vn/");
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='right']")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automatio@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
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