package webdriver;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Wait_Explicit_Exercise {
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
		
		System.out.println(driver.toString());
		
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Not_Enough() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}

	//@Test
	public void TC_02_Equal() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}
	
	//@Test
	public void TC_03_Greater() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(100));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}
	
	//@Test
	public void TC_01_Visible() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Wait for Hello word text visible 
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}

	//@Test
	public void TC_02_Invisible() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Wait for Loading icon invisible => Ít dùng hơn visible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading>img")));
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}
	
	//@Test
	public void TC_03_Text_To_Be() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Wait for Hello Word text  visible by TextToBe
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!")));
		
		//Get Text
		String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		//Verify
		Assert.assertEquals(text, "Hello World!");
		
	}
	
	//@Test
	public void TC_04_Telerik() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Wait cho calendar visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));
		
		//Wait cho chữ "No Selected Dates to display. " visible
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display.")));
		
		//Wait cho ngày được click sẵn sàng clickable=> Wait xong dùng hàm click luôn được
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']/parent::td"))).click();
		
		//Wait cho Loading icon invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1>div.raDiv")));
		
		//Wait + assert text mới xuất hiện
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Thursday, May 11, 2023")));
		
		
	}
	
	@Test
	public void TC_05_Upload_File() {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://gofile.io/welcome");
		
		//Wait cho loading icon invisible
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
		
		//Wait cho button uploadFile clickable => Click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload Files']"))).click();
		
		//Wait cho loading icon invisible
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
		
		//Wait cho add file button is clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add files']")));
		
		//Senkey multiple files
		String small = "small.jpg";
		String sea  = "sea.jpg";
		String medium = "medium.jpg";
		String small_path = projectPath + File.separator + "uploadFiles" + File.separator + small ;
		String sea_path = projectPath + File.separator + "uploadFiles" + File.separator + sea;
		String medium_path = projectPath + File.separator + "uploadFiles" + File.separator + medium;
		driver.findElement(By.xpath("//input[@type ='file']")).sendKeys(small_path + "\n" + sea_path + "\n" + medium_path);
		
		//wait for all icon loading invisible 
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar")))));
		
		//Wait text success visible
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success.text-white"),"Your files have been successfully uploaded")));
		
		//Get Text + mở link
		String linkText = driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).getAttribute("href");
		driver.get(linkText);
		
		//Wait cho loading icon invisible
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
		
		//Verify picture is uploaded successfully
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[@class='contentName' and text()='" + small + "']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[@class='contentName' and text()='" + sea + "']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//span[@class='contentName' and text()='" + medium + "']"))).isDisplayed());
		
	}
	
	@AfterClass

	public void afterClass() {
		driver.quit();
	}
}