package webdriver;
	import java.util.List;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class Topic_13_Default_Dropdown {
		WebDriver driver;//khai báo driver
		Select select;
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
		
		@BeforeClass
		public void beforeClass() {
			if (osName.contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			} else {
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			}
			//Khởi tạo driver
			driver = new ChromeDriver();
			System.out.println(driver.toString());
			
			//Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
			//driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//driver.manage().window().maximize();
			
		}

		@Test
		public void TC_01_Facebook() {
			driver.get("https://www.facebook.com");
			driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
			select = new Select(driver.findElement(By.cssSelector("select#day")));
			//Chọn item 
			//select.selectByIndex(3); => không nên dùng. Do index có thể thay đổi, khó hiểu khi reproduce
			//select.selectByValue("1");=> Không dùng. DO Value k phải thuộc tính bắt buộc, lúc có lúc không, khó hiểu khi reproduce
			select.selectByVisibleText("30"); //=>Thường dùng. DO text bắt buộc phải có, dễ hiểu khi reproduce
			sleepInSecond(3);
			
			//Kiểm tra 1 dropdown có hỗ trợ multiple select hay không
			//select.isMultiple();
			//Assert.assertTrue(select.isMultiple());//nếu mong muốn là multiple
			Assert.assertFalse(select.isMultiple());//nếu mong muốn là single
			
			//Kiểm tra chọn item thành công hay không
			//select.getFirstSelectedOption();
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "30");	
			
			//Lấy ra tất cả các items(option)
			List<WebElement> day = select.getOptions();//kết quả trả về là 31 items
			//Check xem kết quả tổng số items đúng hay không
			//int-int
			Assert.assertEquals(day.size(), 31);
			//int-string
			//Assert.assertEquals(city.size(), "31");
			
			//Muốn in ra tổng số, dùng vòng lặp để in ra
			for (WebElement text: day) {
				System.out.println(text.getText());
			}
			
			
			//Chọn tháng
			select = new Select(driver.findElement(By.cssSelector("select#month")));
			select.selectByVisibleText("May");
			sleepInSecond(3);
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
			Assert.assertFalse(select.isMultiple());
			List<WebElement> month = select.getOptions();
			Assert.assertEquals(month.size(), 12);
			for(WebElement text: month) {
				System.out.println(text.getText());
			}
			
			
			//Chọn năm 
			select = new Select(driver.findElement(By.cssSelector("select#year")));
			select.selectByVisibleText("2023");
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "2023");
			Assert.assertFalse(select.isMultiple());
			List<WebElement> year = select.getOptions();
			Assert.assertEquals(year.size(), 119);
		}
		private void sleepInSecond( long timeout) {
			try {
				Thread.sleep(timeout *1000);
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