package webdriver;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
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