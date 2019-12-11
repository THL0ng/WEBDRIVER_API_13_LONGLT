package webdriver_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_08_Button_RadioButton_Checkbox_Alert {	
		WebDriver driver;
		JavascriptExecutor javascrip;
		Alert alert;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			javascrip = (JavascriptExecutor) driver; 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		public void TC_01_Button() {
			driver.get("http://live.demoguru99.com/");
			
			WebElement buttonLinK = driver.findElement(By.xpath("//a[text()='My Account']"));
			javascrip.executeScript("arguments[0].click();",buttonLinK);
			
			String LoginpageURL = driver.getCurrentUrl();
			Assert.assertEquals(LoginpageURL, "http://live.demoguru99.com/index.php/customer/account/login/"); 
			
			WebElement buttonAcc = driver.findElement(By.xpath("//span[text()='Create an Account']"));
			javascrip.executeScript("arguments[0].click();",buttonAcc);
			
			String CreateAnAccountURL = driver.getCurrentUrl();
			Assert.assertEquals(CreateAnAccountURL, "http://live.demoguru99.com/index.php/customer/account/create/"); 
			
		
		}

		@Test

		public void TC_02_Checkbox() throws InterruptedException {
			// CUSTOM RADIO / CHECK BOX ( BỊ ẨN /USER KO NHÌN THẤY ĐƯỢC : input )
			driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
			
			String checkboxInput = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
			

			// TRƯỜNG HỢP NÀY DO BỊ ẨN NÊN PHẢI DÙNG JS
			// THAM KHẢO TÀI LIỆU TOPIC 9 ĐỂ HIỂU RÕ HƠN TRƯỜNG HỢP NÀY
			clickByJS(checkboxInput);	
			Thread.sleep(3000);
			
			elementSelectedStatus(checkboxInput);
			Assert.assertTrue(isElementSelected(checkboxInput));
			
			
			clickByJS(checkboxInput);	
			Thread.sleep(3000);
			elementSelectedStatus(checkboxInput);
			Assert.assertFalse(isElementSelected(checkboxInput));
		
		}
		
		public void elementSelectedStatus (String locator) {
			WebElement element =driver.findElement(By.xpath(locator));
			if (element.isEnabled()) {
				System.out.println("Element is enabled");
			} else {
				System.out.println("Element is disabled");

			}
		}
		
		public boolean isElementSelected(String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			if (element.isSelected()) {
				return true;
			} else {
				return false;
			}
		}
		
		public void clickByJS (String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			javascrip.executeScript("arguments[0].click();",element);
		}

		@Test

		public void TC_03_RadioButton() throws InterruptedException {
			driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
			
			String radiobutton = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
			clickByJS(radiobutton);	
			Thread.sleep(3000);
			
			elementSelectedStatus(radiobutton);
			Assert.assertTrue(isElementSelected(radiobutton));
		
		} 
		
		@Test

		public void TC_04_AcceptAlert()  {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			String resultmessage = "//p[@id='result']";
			
			// ACCEPT ALERT
			driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
			
			alert = driver.switchTo().alert();
			
			Assert.assertEquals(alert.getText(), "I am a JS Alert");
			alert.accept();
			Assert.assertEquals(driver.findElement(By.xpath(resultmessage)).getText(), "You clicked an alert successfully");
					
		}
		
		
		@Test

		public void TC_05_ConfirmAlert()  {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			String resultmessage = "//p[@id='result']";
			
			// Confirm 
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			
			alert = driver.switchTo().alert();
			
			Assert.assertEquals(alert.getText(), "I am a JS Confirm");
			alert.accept();
			Assert.assertEquals(driver.findElement(By.xpath(resultmessage)).getText(), "You clicked: Ok");
			
			// CANCEL ALERT
			driver.navigate().refresh();
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			
			alert = driver.switchTo().alert();
			
			Assert.assertEquals(alert.getText(), "I am a JS Confirm");
			alert.dismiss();
			Assert.assertEquals(driver.findElement(By.xpath(resultmessage)).getText(), "You clicked: Cancel");
				
		}
		
		
		@Test

		public void TC_06_PromptAlert()  {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			String resultmessage = "//p[@id='result']";
			String fullname = "automation tester";
			
			
			driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
			
			alert = driver.switchTo().alert();
			alert.sendKeys(fullname);
			
			Assert.assertEquals(alert.getText(), "I am a JS prompt");
			alert.accept();
			Assert.assertEquals(driver.findElement(By.xpath(resultmessage)).getText(), "You entered: " + fullname);
			
		} 
		
		
		@Test

		public void TC_07A_AuthenticationAlert()  {
			String UsernameAndPass = "admin";
			String url = "http://the-internet.herokuapp.com/basic_auth";
			url = "http://" +UsernameAndPass+ ":" + UsernameAndPass + "@the-internet.herokuapp.com/basic_auth";
			driver.get(url);
			
		}
		
		
		
		@AfterClass

		public void afterClass() {
		

		}

  
}
