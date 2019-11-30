package webdriver_api;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_07_Exercise_DropDownList {	
		WebDriver driver;
		Select select;
        String Email = "Shenlong" + randomNumber()+"@gmail.com";
		
		public int randomNumber() {
			Random rand= new Random();
			int n =rand.nextInt(5000);
			return n;
	}

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test

		public void TC_01_DropDownList() throws InterruptedException {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
			
			Assert.assertFalse(select.isMultiple());
			
			select.selectByVisibleText("Mobile Testing");
			Thread.sleep(2000);
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
			
			select.selectByValue("manual");
			Thread.sleep(2000);

			Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
			
			select.selectByIndex(9);
			Thread.sleep(2000);

			Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
			
			Assert.assertEquals(select.getOptions().size(), 10);
			
			
			select = new Select(driver.findElement(By.id("job2")));
			
			Assert.assertTrue(select.isMultiple());
			
			select.selectByVisibleText("Automation");
			select.selectByVisibleText("Mobile");
			select.selectByVisibleText("Desktop");
			
			List <WebElement> optionSelected = select.getAllSelectedOptions();
			Assert.assertEquals(optionSelected.size(), 3); 
			List <String> arraySelected = new ArrayList<String>();
			for (WebElement select : optionSelected ) {
				System.out.println(select.getText());
				arraySelected.add(select.getText());
				
			}
			
			Assert.assertTrue(arraySelected.contains("Automation"));
			Thread.sleep(2000);

			Assert.assertTrue(arraySelected.contains("Mobile"));
			Thread.sleep(2000);

			Assert.assertTrue(arraySelected.contains("Desktop"));
			Thread.sleep(2000);

			
			
			select.deselectAll();
			Thread.sleep(3000);

			List <WebElement> optionUnselected = select.getAllSelectedOptions();
			Assert.assertEquals(optionUnselected.size(), 0);		
			
			
		} 

		@Test

		public void TC_02_DropDownList() {
			driver.get("https://demo.nopcommerce.com");	
			
			driver.findElement(By.xpath("//a[text()='Register']")).click();
			
			driver.findElement(By.xpath("//input[@value='M']")).click();
			driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Shen");
			driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Long");
			
			select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
			select.selectByIndex(1);
			Assert.assertEquals(select.getOptions().size(), 32);
			
			select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
			select.selectByVisibleText("May");
			Assert.assertEquals(select.getOptions().size(), 13);
			
			select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
			select.selectByValue("1980");
			Assert.assertEquals(select.getOptions().size(), 112);
			
			driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(Email);
				
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
			
			driver.findElement(By.xpath("//input[@id='register-button']")).click();
			
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='My account']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']")).isDisplayed());
		
		}

		

		@AfterClass

		public void afterClass() {
			

		}

  
}
