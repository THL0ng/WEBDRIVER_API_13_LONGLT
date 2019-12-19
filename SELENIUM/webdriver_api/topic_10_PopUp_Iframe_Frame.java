package webdriver_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_10_PopUp_Iframe_Frame {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test

		public void TC_01_PopUp() {
			driver.get("https://kyna.vn/");
			
			boolean fancyPopup = driver.findElement(By.xpath("//div[@class='fancybox-inner']")).isDisplayed();
			System.out.println("fancy pop up = " + fancyPopup);
			Assert.assertTrue(fancyPopup);
			
			driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();
			
			boolean facebookiframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
			System.out.println("facebook iframe = " + facebookiframe);
			Assert.assertTrue(facebookiframe);
			
			String facebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
			System.out.println("facebook likes number = " + facebookLikes);
			Assert.assertEquals(facebookLikes, "170K likes");

			


			
			
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
			

		}

  
}
