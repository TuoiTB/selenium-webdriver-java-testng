package webdriver;
import java.awt.Checkbox;
import java.io.File;
import java.sql.Driver;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_31_Wait_Mixing_Implicit_Explicit {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<?> fluentWait;
	
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
		
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Element_Found() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.get("https://www.facebook.com/");
		
		//Implicit
		System.out.println("1 - Start time: " + getDateTimeNow());
		driver.findElement(By.xpath("//button[@name='login']"));
		System.out.println("2 - End time: " + getDateTimeNow());
		
		//Explicit
		System.out.println("1 - Start time: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
		System.out.println("2 - End time: " + getDateTimeNow());
	}


	//@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
		System.out.println("1 - Start time: " + getDateTimeNow());
		driver.findElement(By.xpath("//button[@name='selenium']"));
		System.out.println("2 - End time: " + getDateTimeNow());
		
	}
	@Test
	public void TC_02_1_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 0);
		
		driver.get("https://www.facebook.com/");
		
		//Implicit + Explicit
		System.out.println("1 - Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) {
		e.printStackTrace();
		}
		System.out.println("2 - End time: " + getDateTimeNow());
	}
	
	//@Test
	public void TC_03_Element_Not_Found_Implicit_and_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
		//Implicit + Explicit
		System.out.println("1 - Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) {
		e.printStackTrace();
		}
		System.out.println("2 - End time: " + getDateTimeNow());
	}
	
	//@Test
	public void TC_04_Element_Not_Found_Only_Explicit_By() {
		//Nếu k set implicit thì mặc định =0s
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		System.out.println("1 - Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) 
		{
		e.printStackTrace();
		}
		System.out.println("2 - End time: " + getDateTimeNow());
		
	}
	
	//@Test
	public void TC_05_Element_Not_Found_Only_Explicit_WebElement() {
		//Nếu k set implicit thì mặc định =0s
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		System.out.println("1 - Start time: " + getDateTimeNow());
		try {
			//Case 1: Nếu element được tìm thấy thì sẽ chạy tiếp hàm visiblilityOf
			//Case 2: Nếu element không được tìm thấy thì sẽ đánh fail luôn và không chạy hàm visiblilityOf
			WebElement element = driver.findElement(By.xpath("//button[@name='selenium']"));
			explicitWait.until(ExpectedConditions.visibilityOf(element));
			//=> khi sử dụng hạn chế dùng các hàm explicit có tham số là WebElement
			
		} catch (Exception e) 
		{
		e.printStackTrace();
		}
		System.out.println("2 - End time: " + getDateTimeNow());
		
	}
	
	@Test
		public void TC_06_Element_Not_Found_Implicit_Explicit_WebElement() {
			//Nếu k set implicit thì mặc định =0s
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			explicitWait = new WebDriverWait(driver, 5);
			
			driver.get("https://www.facebook.com/");
			System.out.println("1 - Start time: " + getDateTimeNow());
			try {
				//Case 1: Nếu element được tìm thấy thì sẽ chạy tiếp hàm visiblilityOf
				//Case 2: Nếu element không được tìm thấy thì sẽ đánh fail luôn và không chạy hàm visiblilityOf
				WebElement element = driver.findElement(By.xpath("//button[@name='selenium']"));
				explicitWait.until(ExpectedConditions.visibilityOf(element));
				//=> khi sử dụng hạn chế dùng các hàm explicit có tham số là WebElement
				
			} catch (Exception e) 
			{
			e.printStackTrace();
			}
			System.out.println("2 - End time: " + getDateTimeNow());
			
		}
	
	private String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}