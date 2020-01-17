package webdriver_api;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_14_Wait_Part3_Static {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", ".\\LIB\\geckodriver.exe");
			driver = new FirefoxDriver();
			//APPLY ĐỂ CHỜ CHO ELEMENT HIỂN THỊ--> TƯƠNG TÁC VÀO--> FINDELEMENT/FINDELEMENTS
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			//APPLY ĐỂ CHỜ CHO PAGE ĐƯỢC LOAD XONG
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		}

		@Test

		public void TC_01_Static() throws Exception {
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			System.out.println("Start sleep - " + getCurrentTime());
			//STATIC : WAIT CỨNG ( PHẢI CHỜ CHO ĐẾN KHI LOAD ĐỦ GIÂY )
			// NẾU PAGE DC LOAD XONG TRONG 3S--> LÃNG PHÍ 2S
			// NẾU PAGE DC LOAD XONG TRONG 10S--> FAILED 
			// KO FLEXIBLE
			// CHỈ DÙNG TRONG NHỮNG SPECIAL CASE : IE 
			Thread.sleep(5000);
			
			System.out.println("End sleep - " + getCurrentTime());
			driver.findElement(By.id("search_query_top")).sendKeys("Automation");

		}
		
		public String getCurrentTime() {
			Date date = new Date();
			return date.toString();
			
		}

		

		@AfterClass

		public void afterClass() {
			

		}

  
}
