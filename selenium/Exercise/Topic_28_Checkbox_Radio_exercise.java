package Exercise;

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

public class Topic_28_Checkbox_Radio_exercise {
	WebDriver driver;// khai báo driver
	JavascriptExecutor jsExecutor;
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
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}
	//@Test
	public void Exercise_01_Default_Checkbox_and_Radio_button_TELERIK (){
		//Step 01: Truy cập vào trang https://demos.telerik.com/kendo-ui/checkbox/index
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//Step 02: click vào checkbox Dual-xone air conditioning
		WebElement dualZone = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		if (!dualZone.isSelected()) {
			dualZone.click();
		}
		//Step 03: Verify checkbox is selected
		Assert.assertTrue(dualZone.isSelected());
		//Step 04: Bỏ chọn và kiểm tra chưa được chọn
		dualZone.click();
		Assert.assertFalse(dualZone.isSelected());
		//Step 05: Truy cập vào trang http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		//Step 06: Click vào radio button 2.0 petrol, 147kw
		WebElement petrol = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		if (!petrol.isSelected()) {
			petrol.click();
		}
		//Step 07: Kiểm tra radio button đó đã được chọn hay chưa/nếu chưa thì chọn lại
		if (petrol.isSelected()) {
			Assert.assertTrue(petrol.isSelected());
		} else {
			petrol.click();
		}
	}
	
	//@Test
	public void Exercise_02_Default_Checkbox_and_Radio_button_ANGULAR (){
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement summer = driver.findElement(By.xpath("//label[text()=' Summer ']/preceding-sibling::div/input"));
		if (!summer.isSelected()) {
			summer.click();
		}
		if (summer.isSelected()) {
			Assert.assertTrue(summer.isSelected());
		} else {
			summer.click();
		}
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		List<WebElement> twoCheckbox = driver.findElements(By.xpath("//label[text()='Align:']/parent::section/preceding-sibling::section/mat-checkbox//input"));
		for (WebElement checkbox : twoCheckbox) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		for (WebElement checkbox : twoCheckbox) {
			Assert.assertTrue(checkbox.isSelected());
		}
		for (WebElement checkbox : twoCheckbox) {
			checkbox.click();
			Assert.assertFalse(checkbox.isSelected());
		}
	}
	
	//@Test
	public void Exercise_03_Custom_Checkbox_and_Radio_button_COVID19 (){
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		By radio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radio));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(radio).isSelected());
		
	}
	
	@Test
	public void Exercise_04_Custom_Checkbox_and_Radio_button_GOOGLEDOCS (){
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(canThoRadio));
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
		
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