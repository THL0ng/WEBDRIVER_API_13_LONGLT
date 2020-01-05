package webdriver_api;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_12_Javascript_Executor {	
		WebDriver driver;
		JavascriptExecutor jsExecutor;
		WebElement element;
		
		String username = "mngr238966";
		String password = "emYmEqe";
		String customerID;

		
		String customerName = "ShenLong";
		String gender = "male";
		String DateofBirth = "2000-10-10";
		String Address = "221B Baker";
		String City = "london";
		String State = "USA";
		String Pin = "555000";
		String Phone = "0901112220";
		String Email = "Shenlong" + randomNumber()+"@gmail.com";
		
		
		// LOCATOR FOR NEW/EDIT FORM
		By nametextbox = By.name("name");
		By genderradio = By.xpath("//input[@value='m']");
		By genderTextbox = By.name("gender");
		By DOBtextbox = By.name("dob");
		By addressTextarea = By.name("addr");
		By citytextbox = By.name("city");
		By statetextbox = By.name("state");
		By pintextbox = By.name("pinno");
		By phonetextbox = By.name("telephoneno");
		By Emailtextbox = By.name("emailid");
		By passwordtextbox = By.name("password");
		By submitbutton = By.name("sub");
				
				
		
		public int randomNumber() {
			Random rand= new Random();
			int n =rand.nextInt(5000);
			return n;
	}

		@BeforeClass

		public void beforeClass() {
			//driver = new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver", ".\\LIB\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);

			// Set driver for JE lib
			jsExecutor = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		public void TC_01_JS() throws InterruptedException {
			navigateToUrlByJS("http://live.demoguru99.com/");
			
			// NHỮNG HÀM NÀO CẦN GET RA DỮ LIỆU THÌ PHẢI THÊM RETURN VÀO TRƯỚC ĐOẠN JS
			String LiveDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(LiveDomain, "live.demoguru99.com"); // chỉ lấy domain nên ko có http
			
			String LiveURL = (String) executeForBrowser("return document.URL");
			Assert.assertEquals(LiveURL, "http://live.demoguru99.com/");
			
			// cách 1 : clickToElementByJS(driver.findElement(By.xpath("//a[text()='Mobile']")));
			clickToElementByJS("//a[text()='Mobile']"); //cách 2 : truyền locator nên ko cần dùng findelement
			
			highlightElement(("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
			clickToElementByJS(("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
			
			String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));
			
			highlightElement(("//a[text()='Customer Service']"));
			clickToElementByJS(("//a[text()='Customer Service']"));

			String customerserviceTitle = (String) executeForBrowser("return document.title");
			Assert.assertEquals(customerserviceTitle, "Customer Service");
			
			highlightElement("//input[@id='newsletter']");
			scrollToElement("//input[@id='newsletter']");
			Thread.sleep(3000);
			
			pageInnerText = (String) executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
			
			navigateToUrlByJS("http://demo.guru99.com/v4/");
			
			String DemoDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(DemoDomain, "demo.guru99.com");

			
		}

		@Test

		public void TC_02_RemoveAttribute() throws InterruptedException {
			driver.get("http://demo.guru99.com/v4/");
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username); // khai báo biến nên ko sử dụng ""
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password); // khai báo biến nên ko sử dụng ""
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			String homepagewelcomemsg = driver.findElement(By.tagName("marquee")).getText();
			Assert.assertEquals(homepagewelcomemsg, "Welcome To Manager's Page of Guru99 Bank" );
			
				
			Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "']")).isDisplayed());
			
driver.findElement(By.xpath("//a[text()='New Customer']")).click();
			
			// INPUT DATA TO NEW CUSTOMER FORM
			driver.findElement(nametextbox).sendKeys(customerName);
			driver.findElement(genderradio).click();
			
			//REMOVE ATTRIBUTE TYPE='DATE' (DATE OR BIRTH )
			removeAttributeInDOM("//input[@id='dob']", "type");
			Thread.sleep(3000);
			driver.findElement(DOBtextbox).sendKeys(DateofBirth);
			
			driver.findElement(addressTextarea).sendKeys(Address);
			driver.findElement(citytextbox).sendKeys(City);
			driver.findElement(statetextbox).sendKeys(State);
			driver.findElement(pintextbox).sendKeys(Pin);
			driver.findElement(phonetextbox).sendKeys(Phone);
			driver.findElement(Emailtextbox).sendKeys(Email);
			driver.findElement(passwordtextbox).sendKeys(password);
			driver.findElement(submitbutton).click();
			
			
			Assert.assertTrue(driver.findElement(By.xpath("//td[@colspan='2']//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
			
			// VERIFY OUTPUT DATA MATCH VỚI INPUT 
			Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
			Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
			Assert.assertEquals(DateofBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
			Assert.assertEquals(Address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
			Assert.assertEquals(City, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
			Assert.assertEquals(State, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
			Assert.assertEquals(Pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
			Assert.assertEquals(Phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
			Assert.assertEquals(Email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
			
			 customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
			
					
		}

		@Test

		public void TC_03_Created_An_Account() {
			navigateToUrlByJS("http://live.demoguru99.com/");
			
			clickToElementByJS("//a[text()='My Account']");
			
			clickToElementByJS("//span[text()='Create an Account']");
			
			sendkeyToElementByJS("//input[@id='firstname']", "automation");
			sendkeyToElementByJS("//input[@id='lastname']", "FC");
			sendkeyToElementByJS("//input[@id='email_address']", "AUTOMATIONFC" + randomNumber() + "@gmail.com");
			sendkeyToElementByJS("//input[@id='password']", "123456");
			sendkeyToElementByJS("//input[@id='confirmation']", "123456");
			
			clickToElementByJS("//button[@title='Register']");
			
			String InnerText = (String) executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(InnerText.contains("Thank you for registering with Main Website Store."));
			
			clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
			
		
		}

		@AfterClass

		public void afterClass() {
			

		}
		
		
		// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}

  
}
