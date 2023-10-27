package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_Static {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//WebDriverWait explicitWait;
	
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
		
		//explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	@Test
	public void TC_01_Not_Enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Fail vì ít nhất sau 3s nữa Hello text mới xuất hiện
		sleepInSecond(2);
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}

	@Test
	public void TC_02_Equal() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Pass vì sau 5ss  Hello text mới xuất hiện
		sleepInSecond(5);
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}
	
	@Test
	public void TC_03_Greater() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Pass vì sau 5s  Hello text xuất hiện, còn dư tgian=> sleep đủ 10s mới qua step tiếp theo
		sleepInSecond(10);
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
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