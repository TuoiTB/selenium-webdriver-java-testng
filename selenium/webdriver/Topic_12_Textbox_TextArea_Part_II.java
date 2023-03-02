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

public class Topic_12_Textbox_TextArea_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String userName, password, firstName, lastName, employeeID, passportID, issuedDate, expiryDate, comment;

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
		userName = "automation" + rand.nextInt(999);
		firstName = "John";
		lastName = "Wick";
		password = "autoFC123@";
		passportID = "135066835";
		issuedDate = "2018-04-17";
		expiryDate = "2028-04-17";
		comment = "Employer passport\nIdentification Number";

	}

	@Test
	public void TC_01_Textbox_TextArea_HRM() {
		// Step 01: Truy cập vào trang
		// https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Step 02: Login vào hệ thống với User/Pass: Admin/ admin123
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(
				By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"))
				.click();

		// Step 03: Mở trang PIM
		driver.findElement(By.xpath("//span[text()='PIM']")).click();

		// Step 04: Mở trang Add Employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

		// Step 05: Nhập thông tin hợp lệ vào các textbox: FirstName/ LastName
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);

		// Get data từ EmployeeID textbox lưu vào biến để sử dụng cho các step sau
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
		System.out.println(employeeID);
		// Click "Create Login Details"
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label/span")).click();

		// Nhập thông tin hợp lệ vào các textbox: Username/Password/Confirm Password
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

		// Step 06: Click Save button
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();

		// Step 07: Verify dữ liệu đã nhập ở màn hình Add Employee đúng với dữ liệu hiển
		// thị ở trang Persional Detail

		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);

		// Step 08: Click vào tab Immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();

		// Step 09: Click Add tại Assigned Immigration Records
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

		// Step 10: Nhập dữ liệu vào Number/Comments và click Save button
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportID);
		driver.findElement(By.xpath("")).sendKeys(issuedDate);
		driver.findElement(By.xpath("")).sendKeys(expiryDate);
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();

		// Step 11: Verify dữ liệu đã tạo hiển thị đúng
		// Khi truyền biến vào xpath thì cần phải tách chuỗi
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=' " + passportID + " ']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=' " + issuedDate + " ']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=' " + expiryDate + " ']")).isDisplayed());

		// Step 12: Click vào icon Pencil (Edit)
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID );
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issuedDate );
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate );
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment );
		
		// Step 13: Click vào tên User và chon Logout
		driver.findElement(By.cssSelector("span.oxd-userdropdown-tab")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

		// Step 14: Tại màn Login nhập thông tin đã tạo hợp lệ ở Step 5
		 
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("userName");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[contains(string(),' Login ')]")).click();
		
		// Step 15: Vào màn hình My Info
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		
		// Step 16: Verify các thông tin hiển thị đúng: Firstname/ Lastname/ EmployeeID
		Assert.assertEquals(driver.findElements(By.xpath("//input[@name='firstName']")),firstName );
		Assert.assertEquals(driver.findElements(By.xpath("//input[@name='lastName']")),lastName );
		Assert.assertEquals(driver.findElements(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")), employeeID);
		
		// Step 17: Vào màn hình Immigration => Click icon Pencil (Edit)
		
		
		
		// Step 18: Verify các thông tin hiển thị đúng
		
		
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}