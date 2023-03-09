package webdriver;

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

public class Topic_14_Custom_Dropdown {
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

	@Test
	public void TC_01_JQuery() {
		// for
		// if
		// break
		// wait: explicit wait
		// js executor: sroll xuống để thấy item muốn chọn

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		//Chọn số 10:
		// Click vào 1 thẻ nào đó để các items xổ hết ra
		driver.findElement(By.cssSelector("span#number-button")).click();

		// chờ cho tất cả các items được load hết ra
		// đang set là 30s, cần phải lấy location element bao quát hết cả các items
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu li div")));

		// lấy ra hết tất cả các items trong dropdown => trả ra 1 list các items => Lưu
		// lại 1 list
		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu li div"));

		// nếu như item mình cần chọn hiển thị luôn thì chọn luôn được
		// duyệt qua từng item
		// cách 1: dùng for cổ điển: for với index
//			 for (int i = 0; i < allItems.size(); i++) {
//				
//				 //getText từng item
//				String itemText = allItems.get(i).getText();
//				System.out.println(itemText);
//				//kiểm tra text đúng với cái mình cần chọn
//				if(itemText.equals("10")) {
//					
//					//click vào item đó
//					allItems.get(i).click();
//					
//					//thoát khỏi vòng lặp khi tìm thấy item cần
//					break;
//				}
//				
//			}
		// sleepInSecond(4);
		// cách 2: for each: dùng dữ liệu của list, tạo ra 1 biến tạm để duyệt
		for (WebElement tempElement : allItems) {

			// getText từng item
			String itemText = tempElement.getText();
			System.out.println(itemText);
			// kiểm tra text đúng với cái mình cần chọn
			if (itemText.equals("5")) {

				// click vào item đó
				tempElement.click();

				// thoát khỏi vòng lặp khi tìm thấy item cần
				break;
			}
		}
		sleepInSecond(4);
		
		
		//Chọn số 15
		driver.findElement(By.cssSelector("span#number-button")).click();

		// chờ cho tất cả các items được load hết ra
		// đang set là 30s, cần phải lấy location element bao quát hết cả các items
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu li div")));

		// lấy ra hết tất cả các items trong dropdown => trả ra 1 list các items => Lưu
		// lại 1 list
		allItems = driver.findElements(By.cssSelector("ul#number-menu li div"));
		for (WebElement tempElement : allItems) {

			// getText từng item
			String itemText = tempElement.getText();
			System.out.println(itemText);
			// kiểm tra text đúng với cái mình cần chọn
			if (itemText.equals("15")) {

				// click vào item đó
				tempElement.click();

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