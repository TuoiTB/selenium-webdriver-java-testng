package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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

public class Topic_15_Button_Checkbox_Radio {
	WebDriver driver;// khai báo driver
	Select select;
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
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

	}
	
	

	@Test
	public void Button() {
		//Step 01: 
		driver.get("https://www.fahasa.com/customer/account/create");
		
		//Step 02: Navigate tab Đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		
		//Step 03: Verify "Đăng nhập" button là disabled
		By loginButton = By.cssSelector("button.fhs-btn-login");
		Assert.assertFalse((driver.findElement(loginButton)).isEnabled());
		
		//Step 04: Verify "Đăng nhập" button có background color là màu xám rgb(224,224,224)
		String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtonColor = Color.fromString(loginButtonBackgroundColor);
		String loginButtonBackgroundColorHexa = loginButtonColor.asHex();
		
		System.out.println(loginButtonBackgroundColor);
		System.out.println(loginButtonBackgroundColorHexa);
		
		Assert.assertTrue(color.contains("rgb(224,224,224)"));
		
		//Step 05: Input dữ liệu hợp lệ vào Email/Mật khẩu textbox
		driver.findElement(By.id("login_username")).sendKeys("0962065312");
		driver.findElement(By.id("login_password")).sendKeys("a12345678");
		sleepInSecond(2);
		
		//Step 06: Verify "Đăng nhập" button là enabled
		Assert.assertTrue((driver.findElement(loginButton)).isEnabled());
		
		//Step 07: Verify "Đăng nhập" button có background color là màu đỏ rgb = (201,33,39)
		
		
		
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
		// driver.quit();
	}
}