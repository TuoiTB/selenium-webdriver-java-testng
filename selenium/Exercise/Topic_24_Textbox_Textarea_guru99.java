package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_24_Textbox_Textarea_guru99 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String name = "Selenium Online";
	String dob = "10012000";
	String address = "123\nAddress";
	String city = "Ho Chi Minh";
	String state = "Thu Duc";
	String pin = "123456";
	String telephone = "1234567890";
	String email = "UMON95745@gmail.com";
	String pass = "12345678";
	String customerID ;
	String address_2 = "123\nAddress\n123\nAddress";
	String city_2 = "Ho Chi Minh";
	String state_2 = "Thu Duc";
	String pin_2 = "012345";
	String telephone_2 = "0123456789";
	String email_2 = "emailtest2_41524@gmail.com";
	
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
	public void TC_01_Textbox_Textarea_Guru99()  {
		//Step 01: Truy cập vào trang http://demo.guru99.com/v4
		driver.get("http://demo.guru99.com/v4");
		sleepInSecond(3);
		
		//Step 02: đăng nhập với thông tin: 
		//User: mngr483218 / pass: vYjYmet
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mngr483218");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("vYjYmet");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : mngr483218");
		sleepInSecond(2);
		
		//Step 03: Click chọn link New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInSecond(2);
		
		//Step 04: input all valid data=> click Submit
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dob);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(telephone);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		sleepInSecond(5);
		
		//Step 05: Sau khi hệ thống tạo mới Customer thành công => Get ra thông tin của Customer ID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();	
		
		//Step 06: Verify tất cả các thông tin được tạo mới thành công 
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), telephone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email );
		
		//Step 07: chọn Edit Customer => Nhập Customer ID => Submit
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		sleepInSecond(2);
		
		//Step 08: Tại màn hình Edit customer: 
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), name );
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getAttribute("value"), address );
		
		//Step 09: Nhập giá trị mới tại tất cả cá field (Ngoại trừ những field bị disable) => Submit
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address_2);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city_2);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state_2);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin_2);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(telephone_2);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email_2);
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		sleepInSecond(5);
	
		
		//Step 10: verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit thành công
		
		
		
	}
	
	private void sleepInSecond( long timeout) {
		try {
			Thread.sleep(timeout *1000);
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
