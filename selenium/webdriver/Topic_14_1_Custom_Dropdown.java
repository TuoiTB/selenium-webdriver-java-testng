package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_1_Custom_Dropdown {
	WebDriver driver;// khai báo driver
	Select select;
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQuery() {
		// for
		// if
		// break
		// wait: explicit wait
		// js executor: sroll xuống để thấy item muốn chọn

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "2");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "2");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "3");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "3");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "9");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "9");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "16");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "16");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
		
	}
	
	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("//div[@class='ui fluid selection dropdown']","//div[@role='option']/span","Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
		
		
		
	}
	
	
	//@Test
	public void TC_03_Vuejs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}
	
	//@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEditableDropdown("//input[@class='search']","//div[@role='listbox']/div/span","Algeria");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Algeria");
		
	}
	
	
	public void selectItemInEditableDropdown(String xpathTextbox, String xpathChild, String expectedText) {
		driver.findElement(By.xpath(xpathTextbox)).clear();
		driver.findElement(By.xpath(xpathTextbox)).sendKeys(expectedText);
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
		for (WebElement tempElement : allItems) {

			// getText từng item
			String itemText = tempElement.getText();
			System.out.println(itemText);
			// kiểm tra text đúng với cái mình cần chọn
			if (itemText.equals(expectedText)) {

				//Scroll tới element
				//Actions actions = new Actions(driver);
				//actions.moveToElement(tempElement).perform();
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", tempElement);
				//((JavascriptExecutor)driver).executeScript("arguments[0].click();", tempElement);
				sleepInSecond(5);
				
				// click vào item đó
				tempElement.click();
//				sleepInSecond(2);
				// thoát khỏi vòng lặp khi tìm thấy item cần
				break;
			}
		}
		
	}
	
	
	
	public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
		driver.findElement(By.xpath(xpathParent)).click();
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
		for (WebElement tempElement : allItems) {

			// getText từng item
			String itemText = tempElement.getText();
			System.out.println(itemText);
			// kiểm tra text đúng với cái mình cần chọn
			if (itemText.equals(expectedText)) {

				//Scroll tới element
				//Actions actions = new Actions(driver);
				//actions.moveToElement(tempElement).perform();
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", tempElement);
				//((JavascriptExecutor)driver).executeScript("arguments[0].click();", tempElement);
				sleepInSecond(5);
				
				// click vào item đó
				tempElement.click();
//				sleepInSecond(2);
				// thoát khỏi vòng lặp khi tìm thấy item cần
				break;
			}
		}
		
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
		// driver.quit();
	}
}