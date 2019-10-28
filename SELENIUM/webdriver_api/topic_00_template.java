package webdriver_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_00_template {	
		WebDriver driver;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("");

		}

		@Test

		public void TC_01_() {
			driver.get("");
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
