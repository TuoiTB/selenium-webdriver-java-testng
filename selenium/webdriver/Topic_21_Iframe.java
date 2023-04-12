package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Checkbox;
import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

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

public class Topic_21_Iframe {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
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
	
//	@Test
	public void TC_01_IFrame_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(30);
		WebElement facebookiFrame = driver.findElement(By.cssSelector("div.fanpage iframe"));
		Assert.assertTrue(facebookiFrame.isDisplayed());
		//Cần phải switch qua frame / iframe
		//driver.switchTo().frame(0);
		//driver.switchTo().frame(osName);
		driver.switchTo().frame(facebookiFrame);
		String facebookLike = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(facebookLike);
		
		
		//Switch về page trước đó:
		driver.switchTo().defaultContent();
		
		
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Automation FC");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0956874251");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("textarea.meshim_widget_widgets_TextArea")).sendKeys("Java Bootcamp");
		sleepInSecond(2);
		
		
		driver.switchTo().defaultContent();
		String keysearch = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keysearch);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		//Verify course number
		Assert.assertEquals(courseName.size(), 9);
		
		
		for (WebElement course : courseName) {
			System.out.print(courseName);
			Assert.assertTrue(course.getText().contains(keysearch));
		}
	}
	
	@Test
	public void TC_02_Frame_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//Switch to frame
		driver.switchTo().frame("login_page");
		
		//Nhập vào user id
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("Jonhwick");
		
		//Click button continue
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		//Switch qua page default
		driver.switchTo().defaultContent();
		
		//Verify password hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed()); 
		
		
		
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