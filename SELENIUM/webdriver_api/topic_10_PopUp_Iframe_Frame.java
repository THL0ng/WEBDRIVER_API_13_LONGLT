package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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

		public void TC_01_PopUp() throws InterruptedException {
			driver.get("https://kyna.vn/");
			Thread.sleep(5000);
			
			// CASE 1 : CÓ POP UP XUẤT HIỆN
			// CASE 2 : KO CÓ POP UP XUẤT HIỆN
			
			System.out.println("Step 02 - Count popup number");
			List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
			System.out.println("Fancy popup number = " + fancyPopup.size() );
			
			if(fancyPopup.size() > 0 ) {
				System.out.println("Step 3 - Check popup displayed and close it");
				Assert.assertTrue(fancyPopup.get(0).isDisplayed());
				driver.findElement(By.cssSelector(".fancybox-close")).click();
			}
			
			System.out.println("Step 4 - Switch vào Facebook Iframe ");		
			
			
			//PHẢI SWITCH VÀO IFRAME TRƯỚC THÌ MỚI TƯƠNG TÁC VỚI CÁC ELEMENT NẰM TRONG IFRAME
			
			//  DÙNG INDEX
			//driver.switchTo().frame(1);
			
			// DÙNG NAME OR ID (TRƯỜNG HỢP NÀY KO DÙNG DC DO KO CÓ NAME OR ID
			//driver.switchTo().frame("");
			
			// DÙNG WEB ELEMENT
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
			
			boolean facebookiframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
			System.out.println("facebook iframe = " + facebookiframe);
			Assert.assertTrue(facebookiframe);
			
			String facebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
			System.out.println("facebook likes number = " + facebookLikes);
			Assert.assertEquals(facebookLikes, "170K likes");
			
			
			//SAU KHI THAO TÁC XONG PHẢI SWITCH VỀ LẠI MAIN PAGE THÌ MỚI TƯƠNG TÁC VỚI CÁC ELEMENT CỦA MAIN PAGE ĐƯỢC
			
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			
			driver.findElement(By.className("button-login")).click();
			
			driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automationfc.vn@gmail.com");
			Thread.sleep(3000);

			driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("automationfc.vn@gmail.com");
			Thread.sleep(3000);

			//SUBMIT BUTTON SẼ KO CLICK DC DO NHẬP USER/PASS QUÁ NHANH, CHƯA KỊP VALIDATE USER/PASS ( DO DEV CODE = JAVASCRIPT ) 
			driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
			
			WebElement userlogin = driver.findElement(By.xpath("//li[@class='account dropdown wrap']//span[@class='user']"));
			
			Assert.assertTrue(userlogin.isDisplayed());
			
			Assert.assertEquals(userlogin.getText(), "Automation FC");
			

		   


			


			
			
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

  
}
