package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Checkbox;
import java.sql.Driver;
import java.util.List;
import java.util.Random;
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

public class Topic_20_Random_Popup {
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
	
	//@Test
	public void TC_01_Random_Popup_NOT_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		
		sleepInSecond(30);
		
		//List findElements để check
		By firstStepPopup = By.cssSelector("div[data-title='Newsletter Free eBooks']:not([data-page='confirmation'])");
		By secondStepPopup = By.cssSelector("div[data-title='Newsletter Free eBooks'][data-page='confirmation']");
		By firstStepPopup_1 = By.cssSelector("div[data-type='rectangle']");
		List<WebElement> firstStepPopupElement = driver.findElements(firstStepPopup);
		List<WebElement> firstStepPopupElement_1 = driver.findElements(firstStepPopup_1);

		//Case 1: Nếu nó có hiển thị thì nhập thông tin vào và click OK
		if (firstStepPopupElement_1.size()>0 && firstStepPopupElement_1.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Enter your e-mail address']")).sendKeys(emailAddress);
			sleepInSecond(5);
			driver.findElement(By.cssSelector("a[data-label='Get the Books']")).click();
			sleepInSecond(2);
			Assert.assertTrue(driver.findElement(secondStepPopup).isDisplayed());
			sleepInSecond(10);
			
			Assert.assertFalse(driver.findElement(firstStepPopup_1).isDisplayed());
			Assert.assertFalse(driver.findElement(secondStepPopup).isDisplayed());
		}
		
	
		if (firstStepPopupElement.size()>0 && firstStepPopupElement.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Your Email']")).sendKeys(emailAddress);
			sleepInSecond(5);
			driver.findElement(By.cssSelector("a[data-label='OK']")).click();
			sleepInSecond(5);
			Assert.assertTrue(driver.findElement(secondStepPopup).isDisplayed());
			sleepInSecond(10);
			
			Assert.assertFalse(driver.findElement(firstStepPopup).isDisplayed());
			Assert.assertFalse(driver.findElement(secondStepPopup).isDisplayed());
			
		}
		
		//Case 2: Nếu không hiển thị thì qua step tiếp theo
		String keySearch = "Agile Testing Explained";
		
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(keySearch);
		driver.findElement(By.cssSelector("button#search-submit span.tie-icon-search")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.page-title span")).getText(), "Agile Testing Explained");
	}
	
	//@Test
	public void TC_02_Random_Popup_IN_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		
		By popup = By.cssSelector("div.thrv_wrapper.tve_image_caption");
		
		//Luôn có trong HTML thì dùng isDisplayed()
		if (driver.findElement(popup).isDisplayed()== true) {
			driver.findElement(By.cssSelector("div.thrv_wrapper.thrv_icon")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(popup).isDisplayed());
			sleepInSecond(3);
			
			driver.findElement(By.cssSelector("button.btn-danger")).click();
			sleepInSecond(3);
			
			Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");
			
		}
	}
	
	@Test
	public void TC_03_Random_Popup_NOT_In_Dom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		By popup = By.cssSelector("div.popup-content");
		List<WebElement> popupElement = driver.findElements(popup);
		if (popupElement.size()>0 && popupElement.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Nguyễn Văn An");;
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("nguyenvana@gmail.com");;
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0965236841");;
			sleepInSecond(2);
			driver.findElement(By.cssSelector("button.close")).click();
			Assert.assertEquals(driver.findElements(popup).size(), 0);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
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