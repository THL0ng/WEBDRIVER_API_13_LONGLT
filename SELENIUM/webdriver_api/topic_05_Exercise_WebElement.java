package webdriver_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_05_Exercise_WebElement {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

	   @Test

	      public void TC_01_Checkdisplayed() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			WebElement emailtextbox = driver.findElement(By.xpath("//input[@id='mail']"));
			if(emailtextbox.isDisplayed()) {
				emailtextbox.sendKeys("automation testing");
			}
			
	
			WebElement ageunder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
			if(ageunder18.isDisplayed()) {
				ageunder18.click();
			}
			
			
			
			WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
			if(education.isDisplayed()) {
				education.sendKeys("automationt testing");
			}
			
		} 

		@Test

		public void TC_02_CheckEnableOrDisable() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			WebElement emailtextbox = driver.findElement(By.xpath("//input[@id='mail']"));
			if(emailtextbox.isEnabled()) {
				System.out.println("element [" + emailtextbox + " ] is enabled ");
				
			} else {
				System.out.println("element [" + emailtextbox + " ] is disable ");
				
			}
			
			
			WebElement ageunder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
			if(ageunder18.isEnabled()) {
				System.out.println("element [" + ageunder18 + " ] is enabled ");
				
			} else {
				System.out.println("element [" + ageunder18 + " ] is disable ");
				
			}
			
			
			WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
			if(education.isEnabled()) {
				System.out.println("element [" + education + " ] is enabled ");
				
			} else {
				System.out.println("element [" + education + " ] is disable ");
				
			}
			
			
			WebElement jobrole1 = driver.findElement(By.xpath("//select[@id='job1']"));
			if(jobrole1.isEnabled()) {
				System.out.println("element [" + jobrole1 + " ] is enabled ");
				
			} else {
				System.out.println("element [" + jobrole1 + " ] is disable ");
				
			}
			
			
			
			WebElement interest = driver.findElement(By.xpath("//input[@id = 'development']"));
			if(interest.isEnabled()) {
				System.out.println("element [" + interest + " ] is enabled ");
				
			} else {
				System.out.println("element [" + interest + " ] is disable ");
				
			}
			
			
			WebElement slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
			if(slider01.isEnabled()) {
				System.out.println("element [" + slider01 + " ] is enabled ");
				
			} else {
				System.out.println("element [" + slider01 + " ] is disable ");
				
			}
			
			
			WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
			if(password.isEnabled()) {
				System.out.println("element [" + password + " ] is enabled ");
				
			} else {
				System.out.println("element [" + password + " ] is disable ");
				
			}
		
			
			WebElement ageradiobutton = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
			if(ageradiobutton.isEnabled()) {
				System.out.println("element [" + ageradiobutton + " ] is enabled ");
				
			} else {
				System.out.println("element [" + ageradiobutton + " ] is disable ");
				
			}
			
			
			WebElement biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
			if(biography .isEnabled()) {
				System.out.println("element [" + biography  + " ] is enabled ");
				
			} else {
				System.out.println("element [" + biography  + " ] is disable ");
				
			}
			
			
			WebElement jobrole3 = driver.findElement(By.xpath("//select[@id='job3']"));
			if(jobrole3 .isEnabled()) {
				System.out.println("element [" + jobrole3  + " ] is enabled ");
				
			} else {
				System.out.println("element [" + jobrole3  + " ] is disable ");
				
			}
			
			
			WebElement interest1 = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
			if(interest1 .isEnabled()) {
				System.out.println("element [" + interest1  + " ] is enabled ");
				
			} else {
				System.out.println("element [" + interest1  + " ] is disable ");
				
			}
			
			WebElement slider2 = driver.findElement(By.xpath("//input[@id='slider-2']"));
			if(slider2 .isEnabled()) {
				System.out.println("element [" + slider2  + " ] is enabled ");
				
			} else {
				System.out.println("element [" + slider2  + " ] is disable ");
				
			}
		
			
		} 

		@Test

		public void TC_03_checkselected() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
		    boolean age = driver.findElement(By.xpath("//input[@id='under_18']")).isSelected();
		    driver.findElement(By.xpath("//input[@id='development']")).click();
			boolean interest = driver.findElement(By.xpath("//input[@id='development']")).isSelected();
			
			Assert.assertTrue(age);
			Assert.assertTrue(interest);
			
			 driver.findElement(By.xpath("//input[@id='development']")).click();
			 Assert.assertTrue(interest);
		
			
		}

		@AfterClass

		public void afterClass() {
			driver.quit();

		}

  
}
