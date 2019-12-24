package webdriver_api;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_12_Javascript_Executor {	
		WebDriver driver;
		JavascriptExecutor jsExecutor;
		WebElement element;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			// Set driver for JE lib
			jsExecutor = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		public void TC_01_JS() {
			navigateToUrlByJS("http://live.demoguru99.com/");
			
			// NHỮNG HÀM NÀO CẦN GET RA DỮ LIỆU THÌ PHẢI THÊM RETURN VÀO TRƯỚC ĐOẠN JS
			String LiveDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(LiveDomain, "live.demoguru99.com"); // chỉ lấy domain nên ko có http
			
			String LiveURL = (String) executeForBrowser("return document.URL");
			Assert.assertEquals(LiveURL, "http://live.demoguru99.com/");
			
			clickToElementByJS(driver.findElement(By.xpath("//a[text()='Mobile']")));
			
			clickToElementByJS(driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button")));
			
			String pageInnerText = (String) executeForBrowser("return document.documentElement.InnerText");
			Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));

			
		}

		@Test

		public void TC_02_() {
					
		}

		@Test

		public void TC_03_() {
			
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

		public void clickToElementByJS(WebElement element) { 		
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
