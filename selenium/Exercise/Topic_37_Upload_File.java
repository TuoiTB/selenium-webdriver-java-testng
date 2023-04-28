package Exercise;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_37_Upload_File {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String sea = "sea.jpg";
	String small = "small.jpg";
	String sea_path = projectPath + File.separator + "uploadFiles" + File.separator + sea;
	String small_path = projectPath + File.separator + "uploadFiles" + File.separator + small;
	
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Upload_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Upload single file đối với trường hợp upload 1 file
		/*
		 * driver.findElement(By.xpath("//input[@type='file']")).sendKeys(sea_path);
		 * sleepInSecond(2);
		 * Assert.assertTrue(driver.findElement(By.cssSelector("p.name")).isDisplayed())
		 * ; driver.findElement(By.cssSelector("table.table button.start")).click();
		 * sleepInSecond(2);
		 * Assert.assertTrue(driver.findElement(By.xpath("//a[text()='sea.jpg']")).
		 * isDisplayed());
		 */
		 
		
		
		//Upload single file đối với trường hợp upload > 1 file
		By uploadFiles = By.xpath("//input[@type='file']");
		driver.findElement(uploadFiles).sendKeys(sea_path);
		sleepInSecond(2);
		driver.findElement(uploadFiles).sendKeys(small_path);
		sleepInSecond(2);
		
		List<WebElement> startButton = driver.findElements(By.cssSelector("table.table button.start"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + sea + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + small + "']")).isDisplayed());
		
	}
	
	@Test 
	public void TC_02_Upload_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(sea_path + "\n" + small_path);	
		sleepInSecond(2);
		
		List<WebElement> startButton = driver.findElements(By.cssSelector("table.table button.start"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + sea + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + small + "']")).isDisplayed());
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
