package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Checkbox_Radio {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC01_Default_Checkbox() {
		// Step 01: mở page
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(2);

		By dualZoneCheckBox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		// Step 02: Click Dual-zone air conditioning
		if (!driver.findElement(dualZoneCheckBox).isSelected()) {
			driver.findElement(dualZoneCheckBox).click();
			sleepInSecond(2);
		}

		// driver.findElement(By.xpath("//label[text()='Dual-zone air
		// conditioning']/preceding-sibling::input")).click();
		// sleepInSecond(2);
		// Xảy ra 1 rủi ro: chưa biết ban đầu khi mở ra checkbox đã được chọn hay chưa=>
		// do đó nếu đã chọn r thì tc sẽ bị fail

		// Step 03: Verify checkbox ở step 2 đã được chọn
		Assert.assertTrue(driver.findElement(dualZoneCheckBox).isSelected());

		// Step 04: Sau khi check box đã được chọn => bỏ chọn => verify xem được bỏ chọn
		// hay chưa
		if (driver.findElement(dualZoneCheckBox).isSelected()) {
			driver.findElement(dualZoneCheckBox).click();
			sleepInSecond(2);
		}
		Assert.assertFalse(driver.findElement(dualZoneCheckBox).isSelected());
	}

	// @Test
	public void TC_02_Default_Radio() {
		// Step 01: Truy cập vào trang :
		// http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		sleepInSecond(2);
		By petrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By diesel = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

		// Step 02: Click vào radio button 2.0 Petrol, 147kW
		if (!driver.findElement(petrol).isSelected()) {
			driver.findElement(petrol).click();
			sleepInSecond(2);
		}
		// Step 03: Verify radio button đó đã chọn hay chưa/ nếu chưa thì chọn lại
		Assert.assertTrue(driver.findElement(petrol).isSelected());

		// Step 04: Bỏ chọn radio vừa chọn ở step 2
		if (driver.findElement(petrol).isSelected()) {
			driver.findElement(diesel).click();
			sleepInSecond(2);
		}

		// Step 05: Verify petrol được bỏ chọn + diesel được chọn
		Assert.assertFalse(driver.findElement(petrol).isSelected());
		Assert.assertTrue(driver.findElement(diesel).isSelected());
	}

	//@Test
	public void TC_03_Select_All_Checkbox() {
		// Step 01: Truy cập vào trang : https://automationfc.github.io/multiple-fields/
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(2);

		// Step 02: Click vào tất cả các item của checkbox Have you ever had
		// Dùng 1 list lưu tất cả các items vào
		List<WebElement> allItemsCheckbox = driver.findElements(By.xpath("//span[@class='form-checkbox-item']//input"));

		// Click hết toàn bộ thì dùng vòng lặp for
		for (WebElement checkbox : allItemsCheckbox) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
			// Verify toàn bộ các items đã được chọn
			Assert.assertTrue(checkbox.isSelected());
		}

	}

	//@Test
	public void TC_04_Select_Checkbox_Radio_By_Condition() {
		// Step 01: Truy cập vào trang : https://automationfc.github.io/multiple-fields/
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(2);

		// Step 02: Click vào item với điều kiện
		// Dùng 1 list lưu tất cả các items vào
		List<WebElement> allItemsCheckbox = driver.findElements(By.xpath("//span[@class='form-checkbox-item']//input"));
		
		//Nếu checkbox là Gallstones thì mới click
		for (WebElement checkbox : allItemsCheckbox) {
			if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Gallstones")) {
				checkbox.click();
			}
		}
		
		//Verify chỉ có check box là Gallstones được chọn
		for (WebElement checkbox : allItemsCheckbox) {
			if(checkbox.getAttribute("value").equals("Gallstones")) {
				Assert.assertTrue(checkbox.isSelected());
			}
		}
	
	//Đối với radio:
		//Lưu hết các element vào 1 list 
		List<WebElement> exerciseRadio = driver.findElements(By.xpath("//label[contains(text(),' Exercise ')]/following-sibling::div//input"));
		//Nếu là radio 3-4days thì mới click
		for (WebElement radio : exerciseRadio) {
			
			if (!radio.isSelected() && radio.getAttribute("value").equals("3-4 days")) {
			radio.click();
			}
		}
		
		//verify xem radio có được chọn đúng hay chưa
		
		for (WebElement radio : exerciseRadio) {
			if (radio.getAttribute("value").equals("3-4 days")) {
				Assert.assertTrue(radio.isSelected());
			}
		}
	}
	
	//@Test
	public void TC_05_Custom_Radio () {
		//Case 1: Nếu như dùng thẻ input thì không click được + nhưng lại verify được => không khả thi
		//Case 2: Dùng thẻ khác hiển thị để click + không verify được (vì isSelected() chỉ dùng với thẻ input) => Không khả thi
		//Case 3: Dùng thẻ khác input để click + dùng input để verify => Thực tế không dùng cách này
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//		By registerRadioVerify = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
//		By registerRadioClick = By.xpath("//div[text()='Đăng ký cho người thân']");
//		driver.findElement(registerRadioClick).click();
//		sleepInSecond(2);
//		Assert.assertTrue(driver.findElement(registerRadioVerify).isSelected());
		
		//Case 4: Dùng input để click + verify luôn => JS click => Thực tế thường dùng
		//JS không quan tâm element có bị che hay không
		By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(registerRadio));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(registerRadio).isSelected());
	}

	@Test
	public void TC_06_Custom_Radio_Checkbox_docs_google_NOT_INPUT_ELEMENT() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	//Click Radio
		By daNangRadio = By.xpath("//div[@aria-label='Đà Nẵng']");
		
		//verify Đà nẵng radio chưa được chọn
		Assert.assertTrue(driver.findElement(daNangRadio).getAttribute("aria-checked").equals("false"));
		
		//Click vào Đà Nẵng radio 
		driver.findElement(daNangRadio).click();
		sleepInSecond(1);
		//Verify Đà Nẵng radio được chọn thành công
		Assert.assertTrue(driver.findElement(daNangRadio).getAttribute("aria-checked").equals("true"));
		
	//Click 1 Checkbox
		By quangNinhCheckbox = By.xpath("//div[@aria-label='Quảng Ninh' and @id ='i28']");
		Assert.assertEquals(driver.findElement(quangNinhCheckbox).getAttribute("aria-checked"), "false");
		driver.findElement(quangNinhCheckbox).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(quangNinhCheckbox).getAttribute("aria-checked"), "true");
		
	//Click all checkbox
		List<WebElement> allItemCheckbox = driver.findElements(By.xpath("//div[@role='checkbox' and @aria-checked]"));
		//Click all items
		for (WebElement allCheckbox : allItemCheckbox) {
			if (allCheckbox.getAttribute("aria-checked").equals("false")) {
				allCheckbox.click();
				sleepInSecond(1);
			}
		}
		//Verify all items clicked
		for (WebElement allCheckbox : allItemCheckbox) {
			Assert.assertEquals(allCheckbox.getAttribute("aria-checked"), "true");
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