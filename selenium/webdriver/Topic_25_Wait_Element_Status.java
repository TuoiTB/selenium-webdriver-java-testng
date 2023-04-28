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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_Element_Status {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		// Khởi tạo driver
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		System.out.println(driver.toString());
		
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}
	
	//@Test
	public void TC_01_Displayed() {
		driver.get("https://www.facebook.com/");
		//Điều kiện 1: Hiển thị trên UI + IN DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name='login']")));
	}
	
	//@Test
	public void TC_02_Undisplayed_In_DOM() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		
		//Điều kiện 2: element k có trên UI nhưng có trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	
	//@Test
	public void TC_03_Undisplayed_NOT_In_DOM() {
		driver.get("https://www.facebook.com/");
		
		//Điều kiện 3: element k có trên UI và không có trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");
		//ĐK bắt buộc: phải có trong HTML
		//đk 1: element có trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='login']")));
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//ĐK 2: element k có trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	@Test
	public void TC_05_Staleness() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Tại thời điểm này đang có trong html
		WebElement confirmEmailTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		//wait cho confirm email textbox k còn trong html
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
		//có thể dùng invisible thay cho staleness
		explicitWait.until(ExpectedConditions.invisibilityOf(confirmEmailTextbox));
		
		
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