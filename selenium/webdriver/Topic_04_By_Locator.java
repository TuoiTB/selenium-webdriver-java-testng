package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_By_Locator {
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
	public void TC_01_ID() {
		//Tìm element xong thì nhập text vào
		driver.findElement(By.id("email")).sendKeys("Test@automaiton.com");
	}

	@Test
	public void TC_02_Class() {
		//Chỉ tìm element - không tương tác gì lên
		driver.findElement(By.className("search-button"));
	}

	@Test
	public void TC_03_Name() {
		//Chỉ tìm element - không tương tác gì lên
		driver.findElement(By.name("send"));
		driver.findElement(By.name("login[username]"));
	}
	
	@Test
	public void TC_04_Tagname() {
		//Verify 1 page có bao nhiêu element giống nhau: link/ button/ radio/ textbox
		driver.findElements(By.tagName("a")); //verify xem có bao nhiêu thẻ a trong 1 page
		driver.findElements(By.tagName("button"));
		driver.findElements(By.tagName("textbox"));
	}
	
	@Test
	public void TC_05_LinkText() {
		//Chỉ dùng được với Link
		//Lấy tuyệt đối
		driver.findElement(By.linkText("SEARCH TERMS"));
		driver.findElement(By.linkText("ADVANCED SEARCH"));//truyền value tuyệt đối sẽ nhanh hơn tương đối
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		//Chỉ dùng được với Link
		//Lấy tương đối - contains	
		driver.findElement(By.partialLinkText("ADVANCED"));
		driver.findElement(By.partialLinkText("SEARCH"));
	}
	
	@Test
	public void TC_07_Css() {
		//Tất cả 6 trường hợp trên thì Css đều cover được hết
		//Format của Css như sau: tagname[attribute='attribute_value']
		//Css với id
		driver.findElement(By.cssSelector("input#email")); //id dùng dấu #, tagnam#attribute-value
		driver.findElement(By.cssSelector("input[id='email']"));
		
		//Css với class
		driver.findElement(By.cssSelector("div.page-title")); //class dùng dấu . , tagname.attribute-value
		driver.findElement(By.cssSelector("div[class='page-title']"));
		
		//Css với name
		driver.findElement(By.cssSelector("input[name='login[username]']"));
		
		//Css với tagname
		//Chỉ cần truyền mỗi tên thẻ vào , tương tự như đối với "By.tagName"
		driver.findElement(By.cssSelector("a"));
		driver.findElement(By.cssSelector("input"));
		driver.findElement(By.cssSelector("div"));
		
		//Css với link
		driver.findElement(By.cssSelector("a[title='Advanced Search']"));
		
		//Css với partial link : format khác so với những loại còn lại là có thêm dấu *, attribute value chỉ cần contains
		driver.findElement(By.cssSelector("a[title*='Terms'"));
		
	}
	
	@Test
	public void TC_08_Xpath() {
		//Tất cả 7 trường hợp trên thì Xpath đều cover được
		//Cách viết Xpath như sau:    //tagnam[@attribute='attribute_value']
		//Ví dụ: //input[@id='email']
		// Suy ra format của Css (bỏ // và @ ) như sau: tagname[attribute='attribute_value']
		
		//xpath với id
		driver.findElement(By.xpath("//input[@id='email']"));
				
		//xpath với class
		driver.findElement(By.xpath("//div[@class='page-title']"));
				
		//xpath với name
		driver.findElement(By.xpath("//input[@name='login[username]']"));
				
		//xpath với tagname
		//Chỉ cần truyền mỗi tên thẻ vào , tương tự như đối với "By.tagName"
		driver.findElement(By.xpath("//a"));
		driver.findElement(By.xpath("//input"));
		driver.findElement(By.xpath("//div"));
				
		//xpath với link
		driver.findElement(By.xpath("//a[@title='Advanced Search']"));
		driver.findElement(By.xpath("//a[text()='Advanced Search']")); //đối với link, nếu dùng text thì có format khác bthường
				
		//xpath với partial link : format khác so với những loại còn lại là có thêm dấu *, attribute value chỉ cần contains
		driver.findElement(By.xpath("//a[contains(@title,'Terms')]"));
		driver.findElement(By.xpath("//a[contains(text(),'Advanced')]"));
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}