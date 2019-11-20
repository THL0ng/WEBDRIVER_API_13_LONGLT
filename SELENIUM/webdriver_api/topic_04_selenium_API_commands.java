package webdriver_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_04_selenium_API_commands {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		public void TC_01_Browser() {
			// MỞ CÁI AUT (APPLICATION UNDER TEST )
			driver.get("http://live.demoguru99.com/index.php");
			
			// ĐÓNG BROWSER (  1 TAB)
			//driver.close();
			
			// ĐÓNG BROWSER ( TẤT CẢ TAB )
			//driver.quit();
			
			// TRẢ VỀ URL CỦA PAGE HIỆN TẠI
			String homepageurl = driver.getCurrentUrl();
			System.out.println(homepageurl);
			Assert.assertEquals(homepageurl, "http://live.demoguru99.com/index.php");
			
			// TRẢ VỀ TITLE CỦA PAGE HIỆN TẠI
			Assert.assertEquals(driver.getTitle(), "Home page");
			
			// TRẢ VỀ SOURCE CODE CỦA PAGE HIỆN TẠI
			driver.getPageSource();
			Assert.assertTrue(driver.getPageSource().contains("2015 Magento Demo Store. All Rights Reserved."));
			
			// TRẢ VỀ MỘT CÁI WINDOWS ID ( HANDLE WINDOWS )
			String homepagetabID = driver.getWindowHandle();
			System.out.println("window ID = " + homepagetabID);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}

		@Test

		public void TC_02_() {
			driver.get("");			
		}

		@Test

		public void TC_03_() {
			driver.get("");
		}

		@AfterClass

		public void afterClass() {
			driver.quit();

		}

  
}
