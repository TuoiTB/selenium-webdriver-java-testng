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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
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

	
	//@Test
	public void TC_02_Fixed_Popup_In_DOM() {

		driver.get("https://skills.kynaenglish.vn/");
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("div#k-popup-account-login-mb div.modal-content")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automatio@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.xpath("//h4[text()='Đăng nhập']/preceding-sibling::button")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(By.xpath("div#k-popup-account-login-mb div.modal-content")).isDisplayed());		
	}
	
	//@Test
	public void TC_03_Fixed_Popup_NOT_IN_DOM() {
		driver.get("https://tiki.vn/");
		
		By loginButton = By.cssSelector("div.ReactModal__Content");
		
		//Click login button
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInSecond(2);
		
		//Verify popup is displayed
		Assert.assertTrue(driver.findElement(loginButton).isDisplayed());
		
		//Click "Đăng nhập bằng email" link 
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		//Không nhập dữ liệu và click Đăng nhập
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		//Verify text hiển thị
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
		
		//Click để close popup đi
		driver.findElement(By.cssSelector("button.btn-close")).click();
		sleepInSecond(2);
		
		//Verify popup không còn hiển thị(findElements)
			//Dùng findElements số nhiều 
		Assert.assertEquals(driver.findElements(loginButton).size(), 0);
		
	}
	
	@Test
	public void TC_04_Fixed_Popup_NOT_IN_DOM_FB() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		sleepInSecond(2);
		
		By loginRegisterPopup = By.cssSelector("div#reg_box.registration_redesign");
		Assert.assertTrue(driver.findElement(loginRegisterPopup).isDisplayed());
	
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		sleepInSecond(2);
		//Verify các icon error hiển thị
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='firstname']/parent::div/following-sibling::i[contains(@class,'_5dbc')]")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='lastname']/parent::div/following-sibling::i[contains(@class,'_5dbc')]")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email__']/parent::div/following-sibling::i[contains(@class,'_5dbc')]")).isDisplayed());
		
		
		
		
		
		
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(loginRegisterPopup).size(),0);
		
		
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