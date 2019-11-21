package webdriver_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_04_Exercise_selenium_api_commands {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		public void TC_01_verifyURL() {
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			String loginpageurl = driver.getCurrentUrl();
			Assert.assertEquals(loginpageurl, "http://live.demoguru99.com/index.php/customer/account/login/");
			driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			String registerpageurl = driver.getCurrentUrl();
			Assert.assertEquals(registerpageurl, "http://live.demoguru99.com/index.php/customer/account/create/");
			
			
		}
		@Test

		public void TC_02_verifytitle() {
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			String loginpagetitle = driver.getTitle();
			Assert.assertEquals(loginpagetitle, "Customer Login");
			driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			String registerpagetitle = driver.getTitle();
			Assert.assertEquals(registerpagetitle, "Create New Customer Account");
		
		}

		@Test

		public void TC_03_Navigatefunction() {
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			String registerpageurl = driver.getCurrentUrl();
			Assert.assertEquals(registerpageurl, "http://live.demoguru99.com/index.php/customer/account/create/");
			driver.navigate().back();
			String loginpageurl = driver.getCurrentUrl();
			Assert.assertEquals(loginpageurl, "http://live.demoguru99.com/index.php/customer/account/login/");
			driver.navigate().forward();
			String registerpagetitle = driver.getTitle();
			Assert.assertEquals(registerpagetitle, "Create New Customer Account");
		
		}
		
		@Test

		public void TC_04_GetsourceCode() {
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.getPageSource();
			Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
			driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
			driver.getPageSource();
			Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
			
			
		
		}
		
		
		
		

		@AfterClass

		public void afterClass() {
			driver.quit();

		}

  
}
