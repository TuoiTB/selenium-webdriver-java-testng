package webdriver;

import java.sql.Driver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	String firstName = "John";
	String lastName = "Wick";
	String fullName = firstName + " " + lastName;
	String password = "123456789";

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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Textbox_TextArea_Techpanda() {
		// Step 01: Truy cập vào trang http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");

		// Step 02: Click vaof link My Account để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Step 03: Click CREATE AN ACCOUNT button để tới trang đăng ký tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 04: Nhập thông tin hợp lệ vào tất cả các require field: First Name/ Last
		// Name/ Email Address/ Password/ Confirm Password
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		// Step 05: Click Register button
		driver.findElement(By.cssSelector("button[title='Register']")).click();

		// Step 06: Verify msg xuất hiện khi đăng ký thành công
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//li")).getText(),
		"Thank you for registering with Main Website Store.");

		// Step 07: Verify user được tạo mới với thông tin First Name/ Last Name/ Email
		// Address/ hiển thị ở trang My dashboard
		String contactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInfor);
		// Do kết quả trả về là 3 chuỗi giá trị và có xuống dòng nên k thể dùng
		// assertEquals để so sánh
		// thuoooo dfggdgg
		// tu46456577@gmail.com
		// Change Password
		// Dùng so sánh tương đối với contains để so sánh tương đối
		Assert.assertTrue(contactInfor.contains(fullName));
		Assert.assertTrue(contactInfor.contains(emailAddress));

		// Verify First Name/ Last Name/ Email Address/ hiển thị trong trang Account
		// information
		driver.findElement(By.xpath("//a[text()='Account Information']")).click();
		Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);

		// Step 08: Logout khỏi hệ thống
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// Step 09: Kiểm tra hệ thống navigate về Home page sau khi logout thành công
		driver.findElement(By.cssSelector("div.page-title img")).isDisplayed(); // Chỉ chạy qua, không kiểm tra tính đúng sai
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed()); // Kiểm tra tính đúng sai
		Assert.assertTrue(driver.findElement(By.cssSelector("a[title='Additional Options']>img")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}