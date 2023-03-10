package Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Custom_Dropdown_exercise {
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
		explicitWait = new WebDriverWait(driver, 30);
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_JQuery() {
		// for
		// if
		// break
		// wait: explicit wait
		// js executor: sroll xuống để thấy item muốn chọn
		//truy cập đến trang
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//click vào dropdown
//		driver.findElement(By.cssSelector("span#speed-button")).click();
//		
//		//Chờ 30s cho các item hiển thị lên
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu li div")));
//		
//		//Lưu all items vào 1 list
//		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#speed-menu li div"));
//		
//		for (WebElement temporary_var : allItems) {
//			String itemText = temporary_var.getText();
//			if (itemText.equals("Medium") ) {
//				temporary_var.click();
//				break;
//			}
//		}
		selectCustomDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']/li/div","Medium");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Medium");
		
		selectCustomDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']/li/div","Fast");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Fast");
		
		selectCustomDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']/li/div","Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");
		
	}
	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectCustomDropdown("//div[@id='root']","//div[@class='visible menu transition']/div/span","Stevie Feliciano");
		Assert.assertEquals( driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
		
		selectCustomDropdown("//div[@id='root']","//div[@class='visible menu transition']/div/span","Justen Kitsune");
		Assert.assertEquals( driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
	}
	
	@Test
	public void TC_03_VueJS() {
		
	}
	
	public void selectCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
		
		
		driver.findElement(By.xpath(xpathParent)).click();
		
		//Chờ 30s cho các item hiển thị lên
		//explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu li div")));
		
		//Lưu all items vào 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
		
		for (WebElement temporary_var : allItems) {
			String itemText = temporary_var.getText();
			if (itemText.equals(expectedText) ) {
				temporary_var.click();
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