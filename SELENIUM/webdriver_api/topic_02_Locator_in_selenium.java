package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_02_Locator_in_selenium {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();			
		}

		@Test

		public void TC_01_() {
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
			
			//ID
			driver.findElement(By.id("email")).sendKeys("id@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("123456");
			
			//NAME
			driver.findElement(By.name("send")).click();
			
			//CLASS (Newsletter)
			driver.findElement(By.className("validate-email")).clear();
			driver.findElement(By.className("validate-email")).sendKeys("classname@gmail.com");
			
			// TAGNAME (Tìm xem có bao nhiêu đường link ở page này và in giá trị của nó ra)
			// Đếm ( count) bao nhiêu element ở trên page?
			List <WebElement> links = driver.findElements(By.tagName("a")); // số nhiều
			WebElement link = driver.findElement(By.tagName("a")); // số ít
			int linknumber = links.size();
			System.out.println("Tong so link = " + linknumber);
			for (WebElement link1 : links ) {
				System.out.println("Value = " + link1.getAttribute("href"));
			}	
			 //LINK TEXT (LINK)
			 driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
				
			//PARTIAL LINK TEXT (LINK)
			driver.findElement(By.partialLinkText("ORDERS AND ")).click();
			driver.findElement(By.partialLinkText("ORDERS ")).click();
			driver.findElement(By.partialLinkText("AND RETURNS ")).click();
			
			//CSS
			driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("123456");
			driver.findElement(By.cssSelector("input[name='oar_email']")).sendKeys("css_name@gmail.com");
			driver.findElement(By.cssSelector(".input-text.required-entry")).sendKeys("987654");
			
			//XPATH
			
			driver.findElement(By.xpath("//input[@id='oar_order_id']")).sendKeys("xpath_id");
			driver.findElement(By.xpath("//input[@class= 'text.required-entry']")).sendKeys("xpath_class");
			driver.findElement(By.xpath("//input[@name= ' oar_email']")).sendKeys("xpath_name@gmail.com");
			
				
				
				

				
			
				
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
