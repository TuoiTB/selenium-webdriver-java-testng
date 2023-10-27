package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Wait_findElement {
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	
	//@Test
	public void TC_01_findElement() {
		driver.get("https://www.facebook.com/");
		
		//1- Tìm thấy có đúng duy nhất 1 element
		// Khi tìm thấy có duy nhất 1 element thì nó sẽ k cần phải chờ hết time , nhảy qua step tiếp theo luôn
		driver.findElement(By.cssSelector("input#email"));
		
		
		//2- Không tìm thấy element nào hết
		// Nó sẽ có cơ chế tìm lại (mỗi nửa giây sẽ tìm lại 1 lần)
			//Nếu tìm lại mà tìm thấy element thì trả về element đó - k tìm tiếp nữa
			// Nếu tìm lại mà không tìm thấy element nào + hết timeout=> Đánh fail testcase tại chính step đó và ném ra (throws) 1  ngoại lệ : NoSuchElementException
		//3 - Tìm thấy nhiều hơn 1 element 
		// Lấy ra element đầu tiên => Tối ưu từ locate element
		


	}
	
	@Test
	public void TC_02_finElements() {
		driver.get("https://www.facebook.com/");
		List<WebElement> elements;
		
		//1- Tìm thấy 1 element
			//Không cần chờ hết time 
		elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("Duy nhất 1 element: " + elements.size());
		
		//2- Không tìm thấy element nào
			//Nó sẽ có cơ chế tìm lại, mỗi 0,5s sẽ tìm lại 1 lần
			//Tìm lại mà tìm thấy element thì trả về element đó -> k tìm tiếp nữa
			//Tìm lại vân k tìm thấy element và hết time => Sẽ k đánh fail testcase, vẫn tiếp tục chạy step sau, k ném ra exception, trả về 1 list rỗng
			
		
		//3- Tìm thấy nhiều hơn 1 element
		
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}