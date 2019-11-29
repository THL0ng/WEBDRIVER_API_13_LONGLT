package webdriver_api;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
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
