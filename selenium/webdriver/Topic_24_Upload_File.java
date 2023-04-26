package webdriver;

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

public class Topic_24_Upload_File {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String small = "small.jpg";
	String sea  ="sea.jpg";
	
	String small_path = projectPath + File.separator + "uploadFiles" + File.separator + small ;
	String sea_path = projectPath + File.separator + "uploadFiles" + File.separator + sea;
	
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
	
	//@Test
	public void TC_01_Upload_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//upload file
		By uploadFiles = By.xpath("//input[@type ='file']");
		driver.findElement(uploadFiles).sendKeys(small_path);
		sleepInSecond(2);
		
		driver.findElement(uploadFiles).sendKeys(sea_path);
		sleepInSecond(2);
		
		
	
		//Verify ảnh được load lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='small.jpg']")).isDisplayed());
		//Để khi update code k cần update nhiều thì thay tên ảnh thành biến đã khai báo ở trên
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + small + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sea + "']")).isDisplayed());
		
		//Click button Start
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table.table-striped button.start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}
		
		//Verify 
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + small + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + sea + "']")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_02_Upload_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//upload file
		By uploadFiles = By.xpath("//input[@type ='file']");
		driver.findElement(uploadFiles).sendKeys(small_path + "\n" + sea_path );
		sleepInSecond(2);
	
		//Verify ảnh được load lên thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='small.jpg']")).isDisplayed());
		//Để khi update code k cần update nhiều thì thay tên ảnh thành biến đã khai báo ở trên
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + small + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sea + "']")).isDisplayed());
		
		//Click button Start
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table.table-striped button.start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}
		
		//Verify 
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + small + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + sea + "']")).isDisplayed());
		
		
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
		//driver.quit();
	}
}
