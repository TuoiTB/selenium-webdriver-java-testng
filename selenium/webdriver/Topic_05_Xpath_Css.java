package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Css {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.name("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.name("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
		
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		//Step 01: Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Step 02: Nhập dữ liệu valid vào các trường trừ email và confirm email
		driver.findElement(By.id("txtFirstname")).sendKeys("Từ Bảo Tươi");
		driver.findElement(By.id("txtEmail")).sendKeys("Tubaotuoi@gmail.com@123");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tubaotuoi@gmail.com@123");
		driver.findElement(By.id("txtPassword")).sendKeys("a12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("a12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0962065312");
		
		//Step 03: Click vào button Đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Step 04: 
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Register_Incorrect_Email() {
		//Step 1: Truy cập trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Step 2: Nhập dữ liệu hợp lệ vào các field ngoại trừ confirm email
		driver.findElement(By.id("txtFirstname")).sendKeys("Từ Bảo Tươi");
		driver.findElement(By.id("txtEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tubaotuoi1@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("a12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("a12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0962065312");
		
		//Step 03: Click vào button "ĐĂNG KÝ"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Step 04: Kiểm tra error message hiển thị tại Confirm Email
		driver.findElement(By.xpath("//label[text()='Email nhập lại không đúng']"));
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Email nhập lại không đúng']")).getText(),"Email nhập lại không đúng");
		
	}

	@Test
	public void TC_04_Register_Password_Less_Than_6_Characters() {
		//Step 01: Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Step 02: Nhập dữ liệu hợp lệ vào các field ngoại trừ Password và Confirm Password
		driver.findElement(By.id("txtFirstname")).sendKeys("Từ Bảo Tươi");
		driver.findElement(By.id("txtEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0962065312");
		
		//Step 03: Click vào button "ĐĂNG KÝ"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Step 04: Kiểm tra error message hiển thị tại Password và Confirm Password
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Email nhập lại không đúng");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Email nhập lại không đúng");
		
		
	}

	@Test
	public void TC_05_Register_Incorrect_Email() {
		//Step 1: Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Step 2: Nhập dữ liệu hợp lệ vào các field ngoại trừ Confirm Password
		driver.findElement(By.id("txtFirstname")).sendKeys("Từ Bảo Tươi");
		driver.findElement(By.id("txtEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("a1235678");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0962065312");
		
		//Step 3: Click vào button "ĐĂNG KÝ"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Step 4: Kiểm tra error message hiển thị tại Confirm Password
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Register_Invalid_Phone() {
		//Step 1: Truy cập vào trang https://alada.vn/tai-khoan/dang-ky.html
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Step 2: Nhập dữ liệu hợp lệ vào các field ngoại trừ Phone
		driver.findElement(By.id("txtFirstname")).sendKeys("Từ Bảo Tươi");
		driver.findElement(By.id("txtEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("a1235678");
		driver.findElement(By.id("txtCPassword")).sendKeys("a12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("09234");
		
		//Step 3: Click vào button "ĐĂNG KÝ"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Step 4: Kiểm tra error message hiển thị tại Phone
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số. ");
		//Step 5: Nhập dữ liệu hợp lệ vào các field ngoại trừ Phone
		driver.findElement(By.id("txtFirstname")).sendKeys("Từ Bảo Tươi");
		driver.findElement(By.id("txtEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Tubaotuoi@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("a1235678");
		driver.findElement(By.id("txtCPassword")).sendKeys("a12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		//Step 6: Click vào button "ĐĂNG KÝ"
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Step 7: Kiểm tra error message hiển thị tại Phone
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		
		
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}