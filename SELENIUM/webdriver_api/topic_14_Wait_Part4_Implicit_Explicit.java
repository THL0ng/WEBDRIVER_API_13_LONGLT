package webdriver_api;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_14_Wait_Part4_Implicit_Explicit {	
		WebDriver driver;
		WebDriverWait waitexplicit;
		By startButtonBy = By.xpath("//div[@id='start']/button");
		By loadingImageBy = By.xpath("//div[@id='loading']/img");
		By helloWordTextBy = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

		@BeforeClass

		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", ".\\LIB\\geckodriver.exe");
			driver = new FirefoxDriver();
			//System.setProperty("webdriver.chrome.driver", ".\\LIB\\chromedriver.exe");
			//driver = new ChromeDriver();
			

			
			
			
			//WAIT RÕ RÀNG: CHỜ CHO ELEMENT THEO TỪNG TRẠNG THÁI CỤ THỂ
			waitexplicit = new WebDriverWait(driver, 30);
			
			// WAIT NGẦM ĐỊNH:KO CHỜ CHO ELEMENT NÀO CÓ TRẠNG THÁI CỤ THỂ--> CHỈ ĐI TÌM ELEMENT
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 
			driver.manage().window().maximize();

		}

		@Test

		public void TC_01_Implicit_Wait() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// CHECK CHO ELEMENT ĐƯỢC HIỂN THỊ (VISIBLE)
			WebElement startbutton = driver.findElement(startButtonBy);
			Assert.assertTrue(startbutton.isDisplayed());
			
			//CLICK VÀO START BUTTON
			System.out.println("Start click - " + getCurrentTime());
			startbutton.click();
			System.out.println("End click - " + getCurrentTime());
			
			//LOADING ICON HIỂN THỊ VÀ MẤT 5S ĐỂ HIỂN THỊ VÀ BIẾN MẤT
			// NẾU SET LẠI 10S CHO IMPLICIT WAIT ( 3S KO CÒN Ý NGHĨA ) --> BỊ GHI ĐÈ
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	
			//CHECK CHO HELLOWORLD ĐƯỢC HIỂN THỊ
			System.out.println("Start helloworld - " + getCurrentTime());
			WebElement helloWorldText = driver.findElement(helloWordTextBy);
			System.out.println("End helloworld - " + getCurrentTime());
		
			System.out.println("Start displayed - " + getCurrentTime());
			Assert.assertTrue(helloWorldText.isDisplayed());
			System.out.println("End displayed - " + getCurrentTime());

			
		}
		
		public String getCurrentTime() {
			Date date = new Date();
			return date.toString();
			
		}

		@Test

		public void TC_02_Implicit_Override() {
			
			//KHI BỊ OVERRIDE NÓ SẼ SET LẠI CHO TẤT CẢ TESTCASE/STEPS CÒN LẠI
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// CHECK CHO ELEMENT ĐƯỢC HIỂN THỊ (VISIBLE)
			WebElement startbutton = driver.findElement(startButtonBy);
			Assert.assertTrue(startbutton.isDisplayed());
			
			//CLICK VÀO START BUTTON
			System.out.println("Start click - " + getCurrentTime());
			startbutton.click();
			System.out.println("End click - " + getCurrentTime());
			
			//LOADING ICON HIỂN THỊ VÀ MẤT 5S ĐỂ HIỂN THỊ VÀ BIẾN MẤT
		
			//CHECK CHO HELLOWORLD ĐƯỢC HIỂN THỊ
			System.out.println("Start helloworld - " + getCurrentTime());
			WebElement helloWorldText = driver.findElement(helloWordTextBy);
			System.out.println("End helloworld - " + getCurrentTime());
		
			System.out.println("Start displayed - " + getCurrentTime());
			Assert.assertTrue(helloWorldText.isDisplayed());
			System.out.println("End displayed - " + getCurrentTime());
		
		}
		
		@Test

		public void TC_03_Explicit_Wait_Visible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			//CLICK START BUTTON 
			waitexplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));	
			driver.findElement(startButtonBy).click();
			
			//LOADING ICON HIỂN THỊ VÀ BIẾN MẤT SAU 5S
			
			//WAIT CHO HELLOWROLD ĐƯỢC VISIBLE TRƯỚC KHI CHECK DISPLAYED
			System.out.println("Start wait visible - " + getCurrentTime());
			waitexplicit.until(ExpectedConditions.visibilityOfElementLocated(helloWordTextBy));
			System.out.println("End wait visible - " + getCurrentTime());

			
			System.out.println("Start displayed - " + getCurrentTime());
			Assert.assertTrue(driver.findElement(helloWordTextBy).isDisplayed());
			System.out.println("End displayed - " + getCurrentTime());		
		}
		
		
		@Test

		public void TC_04_Explicit_Wait_invisible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			//CLICK START BUTTON 
			waitexplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));	
			driver.findElement(startButtonBy).click();
			
			//LOADING ICON HIỂN THỊ VÀ BIẾN MẤT SAU 5S
			System.out.println("Start wait invisible - " + getCurrentTime());
			waitexplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
			System.out.println("End wait invisible - " + getCurrentTime());


			
			//WAIT CHO HELLOWROLD ĐƯỢC VISIBLE TRƯỚC KHI CHECK DISPLAYED
			System.out.println("Start wait visible - " + getCurrentTime());
			waitexplicit.until(ExpectedConditions.visibilityOfElementLocated(helloWordTextBy));
			System.out.println("End wait visible - " + getCurrentTime());

			
			System.out.println("Start displayed - " + getCurrentTime());
			Assert.assertTrue(driver.findElement(helloWordTextBy).isDisplayed());
			System.out.println("End displayed - " + getCurrentTime());		
		}
		
		@Test

		public void TC_05_Date_Explicit() {
			driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
			
			//IN RA NGÀY ĐƯỢC CHỌN : NO SELECTED DATES TO DISPLAY
			WebElement dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
			System.out.println("Date selected = " + dateSelectedText.getText());
			Assert.assertEquals(dateSelectedText.getText(), "No Selected Dates to display.");

			//CLICK VÀO CURRENT DAY
			driver.findElement(By.xpath("//a[text()='7']")).click();
			
			//CHỜ CHO LOADING ICON BIẾN MẤT
			waitexplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position: absolute;')]/div[@class='raDiv']")));

			
			//CHECK CURREND DAY = SELECTED
			Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[contains(text(),'7')]")).isDisplayed());
			
			dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
			System.out.println("Date selected = " + dateSelectedText.getText());
			Assert.assertEquals(dateSelectedText.getText(), "Tuesday, January 07, 2020");

			

			
			
			
		}

		@AfterClass

		public void afterClass() {
			
		}

  
}
