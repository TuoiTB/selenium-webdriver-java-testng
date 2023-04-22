package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_JavascriptExecutor {
	WebDriver driver;// khai báo driver
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String emailAddress = "automation" + rand.nextInt(999) + "@gmail.vn";
	String password = "automation123@";
	String firstName = "Auto";
	String lastName = "Test";
	String middleName = "FC";
	JavascriptExecutor jsExecutor;
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
		//driver = new FirefoxDriver();
		System.out.println(driver.toString());
		
		//ÉP kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		// Khi khởi tạo cần biến driver thì mới khởi tạo ở @BeforeClass
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}
	
	//@Test
	public void TC_01_LiveGuru() {
		// tất cả các testcase trong bài này đều dùng js executor
		//Truy cập vào trang liveguru
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		
		//Get domain và verify
		executeForBrowser("return document.domain;");
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		
		//Get url và verify url
		executeForBrowser("return document.URL;");
		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.techpanda.org/");
		
		//Open Mobile page 
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		//Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		//Verify msg được hiển htij, sử dụng JE, Get innertext
			//Cách 1: dùng hàm getInnerText()
			Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
			//Cách 2: Dùng hàm areExpectedTextInInnerText()
			Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		//Open Customer Service page (sử dụng JE)
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		
		//Verify title của page = Customer Service (sử dụng JE)
		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		//Scroll tới element Newslater textbox nằm ở cuối page (sử dụng JE)
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		
		
		//Input email hơp lệ vào Newsletter textbox 
		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress );
		
		//Click vào Subcribe button 
		hightlightElement("//button[@class='button']");
		clickToElementByJS("//button[@class='button']");
		
		//Verify text có hiển thị "Thank you...."(sử dụng JE-get inner text)
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		//Navigate tới domain http://demo.guru99.com/v4/ 
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		
		//Verify domain
		String guruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(guruDomain, "demo.guru99.com");
	}
	
	//@Test
	public void TC_02_Rode() {
		driver.get("https://warranty.rode.com/");
		//Click button register
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		sleepInSecond(3);
		
		// Verify msg validation
		getElementValidationMessage("//input[@id='firstname']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");
		
		//senkeys
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Automation");
		//Click register
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");
		
		//Senkeys + click
		driver.findElement(By.xpath("//input[@id='surname']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='email']"), "Please fill out this field.");
		
		
		//Senkeys email sai dịnh dạng + click
		//driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='email']")).sendKeys("tubaotuoi@gmail@com");
		//driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		//sleepInSecond(3);
		//Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='email']"), "Please enter an email address.");
		//driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='email']")).clear();
		
		//Senkeys + click
		driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='password']"), "Please fill out this field.");
		
		//Senkeys + click
		driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div/form[@method='POST']/div//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='password-confirm']"), "Please fill out this field.");
		
		//Senkeys
		driver.findElement(By.xpath("//input[@id='password-confirm']")).sendKeys(password);
		
	}

	@Test
	public void TC_03_Tech_Panda() {
		
		navigateToUrlByJS("http://live.techpanda.org/");
		hightlightElement("//div[@class='account-cart-wrapper']//a");
		clickToElementByJS("//div[@class='account-cart-wrapper']//a");
		
		hightlightElement("//div[@id='header-account']//li[@class='first']");
		clickToElementByJS("//div[@id='header-account']//li[@class='first']/a");
		sleepInSecond(2);
		
		clickToElementByJS("//div[@class='content']/following-sibling::div[@class='buttons-set']//a");
		sleepInSecond(2);
		
		sendkeyToElementByJS("//input[@id='firstname']", firstName);
		sendkeyToElementByJS("//input[@id='middlename']", middleName);
		sendkeyToElementByJS("//input[@id='lastname']", lastName);
		sendkeyToElementByJS("//input[@id='email_address']", emailAddress);
		sendkeyToElementByJS("//input[@id='password']", password);
		sendkeyToElementByJS("//input[@id='confirmation']", password);
		sleepInSecond(2);
		
		hightlightElement("//button[@title='Register']");
		clickToElementByJS("//button[@title='Register']");
		sleepInSecond(2);
		
		Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//div[@id='header-account']//li[@class=' last']/a");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2//img")).isDisplayed());
		
	}
	
	
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}