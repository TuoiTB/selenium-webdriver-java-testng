package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_Commands {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_01_Browser() {
		//Các command/hàm để tương tác với Browser thì nó thông qua biến driver
		//Dùng để đóng tab hiện tại hoặc có thể đóng browser nếu chỉ có 1 tab trên browser đó
		//Dùng khi handle windows/tab
		driver.close(); 
		
		//Đóng browser. Không quan tâm bao nhiêu tab. đóng toàn bộ cả browser
		driver.quit(); 
		
		//Tìm 1 element với locator nào đó (id/class/name/css/xpath/...)
		driver.findElement(By.id(""));
		
		//Tìm nhiều element với nhiều locator nào đó. Ví dụ cần tìm nhiều checkbox/radio
		driver.findElements(By.xpath("//a"));
		driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		//Mở ra 1 page url nào đó
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.get("http://live.techpanda.org/index.php");
		
		//Lấy ra url của page hiện tại. Đang đứng tại page nào thì lấy của page đó
		driver.getCurrentUrl();
		
		//So sánh actual với expected:
		//Cách 1: (Dùng khi chỉ sử dụng 1 lần)
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		//Cách 2: (Dùng khi sử dụng biến cho nhiều step khác nhau, vì khai báo biến nhiều sẽ tốn bộ nhớ)
		String homeURL = driver.getCurrentUrl();
		Assert.assertEquals(homeURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Lấy ra code HTML/CSS/JS của page , để verify 
		//Cách này rất ít dùng
		//Lưu ý: Không dùng asserEquals với getPageSoure()
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Registration is free and easy!"));
		
		
		//Lấy ra Title của page hiện tại. Dùng để kiểm tra title của page hiện tại xem đúng hay sai
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(),"Custermer Login" );
		
		
		// Window/Tab
		//Lấy ra id của tab/window mà driver đang đứng
		driver.getWindowHandle();
		
		//Lấy ra tất cả id của các tab/window
		driver.getWindowHandles();
		
		//Cookies
		driver.manage().getCookies();
		
		//Log của browser:
		driver.manage().logs().get(LogType.BROWSER);
		
		//define ra 1 đơn vị thời gian để chờ cho element xuất hiện trong bao lâu
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.DAYS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
		
	}
	
	@Test
	public void TC_02_Element() {
		
	}
	
	@Test
	public void TC_03_Tips() {
		//Chia ra 3 nhóm chính
		//Nhóm 1- Hàm để tương tác /action
		//Tên hàm sẽ thể hiện rõ chức năng của hàm đó
		//Không trả về (return) dữ liệu gì 
		driver.findElement(By.xpath("")).click();
		driver.findElement(By.xpath("")).sendKeys("");
		//Ví dụ: click/sendkeys/ select/ findElement/...
		
		
		//Nhóm 2-Lấy ra dữ liệu cho mục đích nào đó (step tiếp theo/ step hiện tại), thường mục đích là để verify dữ liệu
		//Nó sẽ bắt đầu bằng tiền tố là getXXX
		//Trả về dữ liệu, thường là dữ liệu String
		//getText/ getCurrentUrl/ getTitle/ getCssValue/...
		//Dùng để kiểm tra dữ liệu thực tế (Actual results) có bằng với dữ liệu mong muốn(Expected Results) hay không (equals)-> Hàm Assert
		//Assert có thư viện riêng, k phải của Selenium: JUnit/ TestNG, AssertJ/ Hamcrest/...
		
		
		//Nhóm 3- Kiểm tra dữ liệu
		//Dùng để kiểm tra tính đúng đắn. Hay dùng với True/False
		//Trả về dữ liệu. Hầu hết dữ liệu trả về là Boolean
		// isDisplayed/isEnabled/ isSelected/ isMultiple /...
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
