package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Exercises_part_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Emty_Email_Password() {
		// Step 1: Truy cập tới trang
		driver.get("http://live.techpanda.org/");

		// Step 2: Click link "My account"
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 3: Để trống username và password
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");

		// Step 4: Click login button
		driver.findElement(By.id("send2")).click();

		// Step 5: Verify error msg
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	//@Test
	public void TC_02_Invalid_Email() {
		// Step 1: Truy cập tới trang
		driver.get("http://live.techpanda.org/");

		// Step 2: Click link "My account"
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 3: Nhập valid email+pass
		driver.findElement(By.id("email")).sendKeys("12345@567890.1224");
		driver.findElement(By.id("pass")).sendKeys("123456");

		// Step 4: Click login button
		driver.findElement(By.id("send2")).click();

		// Step 5: Verify error msg
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	//@Test
	public void TC_03_Password_less_than_6_characters() {
		// Step 1: Truy cập tới trang
		driver.get("http://live.techpanda.org/");

		// Step 2: Click link "My account"
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 3: Nhập valid email + invalid pass
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");

		// Step 4: Click login button
		driver.findElement(By.id("send2")).click();

		// Step 5: Verify error msg
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	//@Test
	public void TC_04_Incorrect_Email_Password() {
		// Step 1: Truy cập tới trang
		driver.get("http://live.techpanda.org/");

		// Step 2: Click link "My account"
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 3: Nhập correct email + incorrect pass
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");

		// Step 4: Click login button
		driver.findElement(By.id("send2")).click();

		// Step 5: Verify error msg
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(),
				"Invalid login or password.");
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
