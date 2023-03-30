package webdriver;

import java.awt.AWTException;
import java.awt.Checkbox;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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

public class Topic_18_Action_User_Interaction {
	WebDriver driver;// khai báo driver
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String dragDropFile = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		// Khởi tạo driver
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		System.out.println(driver.toString());
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		System.out.println(driver.toString());
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}

	//@Test
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
	
	//@Test
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
	
	//@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement clickItem = driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"));
		if (driver.toString().contains("Firefox")) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);",clickItem);
		}
		
		action.doubleClick(clickItem).perform();
		sleepInSecond(3);
		
		WebElement verifyContent = driver.findElement(By.cssSelector("p#demo"));
		Assert.assertEquals(verifyContent.getText(), "Hello Automation Guys!");
		
	}
	
	//@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Verify quit chưa hiển thị
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		//right click vào button
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		
		//Kiểm tra quit hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
		//hover vào quit
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		//Quit sẽ update thêm trạng thái hover=>verify trạng thái này
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());
		
		//Click vào quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		//Accept alert
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		
		//Verify quit không còn
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
	}
	
	//@Test
	public void TC_07_Drag_and_Drop_HTML4() {
		//drag and drop được list vào trong những case không nên làm automationtest
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		action.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")), driver.findElement(By.cssSelector("div#droptarget"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color")).asHex(),"#03a9f4");
	}
	
	//@Test
	public void TC_08_Drag_and_Drop_HTML5_CSS() throws IOException {
		//drag and drop được list vào trong những case không nên làm automationtest
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String dragAndDropContent = getContentFile(dragDropFile);
		
		//Drag from A to B
		jsExecutor.executeScript(dragAndDropContent);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "A");
		
		//Drag from B to A
		jsExecutor.executeScript(dragAndDropContent);
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "B");
		
		
		
		
	}
	
	@Test
	public void TC_09_Drag_and_Drop_HTML5_Xpath() throws AWTException {
		//drag and drop được list vào trong những case không nên làm automationtest
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		dragAndDropHTML5ByOffset("div#column-a","div#column-b");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "A");
		
		dragAndDropHTML5ByOffset("div#column-b","div#column-a");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b header")).getText(), "B");
		
		
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public void dragAndDropHTML5ByOffset(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.cssSelector(sourceLocator));
		WebElement target = driver.findElement(By.cssSelector(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}