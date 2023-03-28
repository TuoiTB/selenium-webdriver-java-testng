package webdriver;

import java.awt.Checkbox;
import java.awt.RenderingHints.Key;
import java.sql.Driver;
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

public class Topic_18_Action_Part_I {
	WebDriver driver;// khai báo driver
	Actions action;
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
		action = new Actions(driver);
		System.out.println(driver.toString());
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
//	@Test
	public void TC_01_Hover_To_Tooltip_AutomationFC() {
		//Di chuột tới element trước rồi mới click 
		//action.click(driver.findElement(By.cssSelector("")));
		
		//Không di chuột, click luôn
		//driver.findElement(By.cssSelector("")).click();;
		
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
	
	}
	
	//@Test
	public void TC_02_Hover_To_Element_Healthkart() {
		
		driver.get("https://www.healthkart.com/");
		
		//Hover vào Customer support
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Customer Support']"))).perform();
		sleepInSecond(2);
		
		//Click chuột trái vào "Term & condition"
		action.click(driver.findElement(By.xpath("//div[@class='d-table-left']/a[text()='Terms & Conditions']"))).perform();
		sleepInSecond(2);
		
		//verify đã chuyển page sau khi click thành công
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Terms and Conditions']")).getText(), "Terms and Conditions");
		
		
	}
	//@Test
	public void TC_03_Click_and_Hold_Select_Multiple_Block() {
		//Step 1: Truy cập trang
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Cách 1:
		//Step 2: Click and hold từ 1->4
		//Click chuột trái và giữ 1 số bắt đầu
		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']"))).perform();
		
		//Kéo chuột đến số kết thúc
		action.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).perform();
		action.release().perform();
		sleepInSecond(2);
		//Sau khi chọn- kiểm tra có đúng 4 phần tử đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected' and text()='1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected' and text()='2']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected' and text()='3']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected' and text()='4']")).isDisplayed());
		
		//Cách 2: 
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));	
		
		action.clickAndHold(allNumber.get(1)).moveToElement(allNumber.get(7)).release().perform();
		sleepInSecond(3);
		
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(),6);
		
	}
	@Test
	public void TC_04_Click_and_Hold_Select_Multiple_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));	
		action.keyDown(Keys.CONTROL).perform();
		action.click(allNumber.get(0))
		.click(allNumber.get(2))
		.click(allNumber.get(5))
		.click(allNumber.get(8))
		.click(allNumber.get(10)).perform();
		
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(),5);
		
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