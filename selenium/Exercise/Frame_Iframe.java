package Exercise;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Frame_Iframe {
	WebDriver driver;// khai báo driver
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	//@Test
	public void TC_01_Iframe_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(30);
		
		WebElement FB_Iframe = driver.findElement(By.cssSelector("div.face-content>iframe"));
		Assert.assertTrue(FB_Iframe.isDisplayed());
		
		driver.switchTo().frame(FB_Iframe);
		String textFacebookLike = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(textFacebookLike, "165K likes");
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("062065384");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Bootcamp");
		sleepInSecond(3);
		
		driver.switchTo().defaultContent();
		String keySearch = "Excel";
		driver.findElement(By.name("q")).sendKeys(keySearch);
		driver.findElement(By.cssSelector("button.search-button")).click();
		List<WebElement> listElementCourse = driver.findElements(By.cssSelector("div.content h4"));
		Assert.assertEquals(listElementCourse.size(), 9);
		for (WebElement course : listElementCourse) {
			System.out.print(course);
			Assert.assertTrue(course.getText().contains(keySearch));
		}
		
	}
		
	@Test
	public void TC_02_Frame_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("john");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
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