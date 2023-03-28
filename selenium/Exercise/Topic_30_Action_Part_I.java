package Exercise;

import java.awt.Checkbox;
import java.awt.Desktop.Action;
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

public class Topic_30_Action_Part_I {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
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
		driver.manage().window().maximize();
	}
	//@Test
	public void TC_01_Hover_To_Element_Tooltip_1() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
	}
	
	//@Test
	public void TC_02_Hover_To_Element_Tooltip_2() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		action.click(driver.findElement(By.xpath("//a[text()='Girls Clothing']//parent::li//following-sibling::li//a[text()='Dresses']"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("h1.title-title")).getText().equals("Kids Wear Online Store"));
	}
	
	//@Test
	public void TC_03_Hover_To_Element_Tooltip_3() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.xpath(""))).perform();
		
	}
	
	@Test
	public void TC_04_Click_and_hold_block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
		action.clickAndHold(allNumber.get(1)).moveToElement(allNumber.get(5)).release().perform();
		sleepInSecond(3);
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 2);
	}
	
	@Test
	public void TC_05_Click_and_hold_random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
		action.keyDown(Keys.CONTROL).click(allNumber.get(0))
		.click(allNumber.get(2))
		.click(allNumber.get(5))
		.click(allNumber.get(10)).perform();
		sleepInSecond(3);
		
		List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 4);
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