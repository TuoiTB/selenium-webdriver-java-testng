package webdriver;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Action_Part_I {
	WebDriver driver;// khai báo driver
	Actions action;
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
		action = new Actions(driver);
		System.out.println(driver.toString());
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
//	@Test
	public void TC_01_Hover_To_Tooltip_AutomationFC() {
		//Di chuột tới element trước rồi mới click 
		//action.click(driver.findElement(By.cssSelector("")));
		
		//Không di chuột, click luôn
		//driver.findElement(By.cssSelector("")).click();;
		
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
	
	}
	
	//@Test
	public void TC_02_Hover_To_Element_Healthkart() {
		
		driver.get("https://www.healthkart.com/");
		
		//Hover vào Customer support
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Customer Support']"))).perform();
		sleepInSecond(2);
		
		//Click chuột trái vào "Term & condition"
		action.click(driver.findElement(By.xpath("//div[@class='d-table-left']/a[text()='Terms & Conditions']"))).perform();
		sleepInSecond(2);
		
		//verify đã chuyển page sau khi click thành công
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Terms and Conditions']")).getText(), "Terms and Conditions");
		
		
	}
	@Test
	public void TC_03_Click_and_Hold_Select_Multiple() {
		//Step 1: Truy cập trang
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Step 2: Click and hold từ 1->4
		//Click chuột trái và giữ 1 số bắt đầu
		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']"))).perform();
		
		//Kéo chuột đến số kết thúc
		action.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).perform();
		//action.release().perform();
		sleepInSecond(2);
		//Sau khi chọn- kiểm tra có đúng 4 phần tử đã được chọn thành công
		
		
		
		
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