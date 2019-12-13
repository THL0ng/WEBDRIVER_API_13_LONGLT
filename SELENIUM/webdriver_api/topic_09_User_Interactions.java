package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_09_User_Interactions {	
		WebDriver driver;
		Actions action; 

		@BeforeClass

		public void beforeClass() {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.webnotification.enabled" , false);
			driver = new FirefoxDriver(profile);
			action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test

		public void TC_01_Hover_Move_Mouse() {
			driver.get("https://www.myntra.com/");
			
			action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Discover']"))).perform();
			
			driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Adidas']")).click();
			
			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='ADIDAS']")).isDisplayed());
			
			Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title']")).isDisplayed());

		
		}

		@Test

		public void TC_02_Click_And_Hold() throws InterruptedException {
			driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
			
			List <WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
			int numberSize = numbers.size();
			System.out.println("Size " + numberSize);
			
			action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform(); // release là nhả chuột để chọn ra sau khi kéo và giữ
			Thread.sleep(3000);
			
			List <WebElement> SelectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
			System.out.println("Size before click/hold " + SelectedNumber.size());
			
			for (WebElement number :SelectedNumber ) {
				System.out.println(number.getText());
			}

			Assert.assertEquals(SelectedNumber.size(), 8); // .size ( kiểu int ) nên ko thể truyền chuỗi "" vì sẽ ko thể so sánh
				
		}

		@Test

		public void TC_03_Click_And_Select_Random() {
			driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
			
			List <WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
			int numberSize = numbers.size();
			System.out.println("Size " + numberSize);
			
			action.keyDown(numbers.get(0), Keys.CONTROL).perform();
			action.click(numbers.get(0))
			.click(numbers.get(5))
			.click(numbers.get(3))
			.click(numbers.get(9))
			.perform();
			action.keyUp(numbers.get(0), Keys.CONTROL).perform();

			List <WebElement> SelectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
			System.out.println("Size before click/hold " + SelectedNumber.size());
			
			for (WebElement number :SelectedNumber ) {
				System.out.println(number.getText());
			}

			Assert.assertEquals(SelectedNumber.size(), 4);
		
		}
		
		
		@Test
		public void TC_04_DoubleClick() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
			
			String DemoText = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			Assert.assertEquals(DemoText, "Hello Automation Guys!");
			
			
			
			
			
		}


		@AfterClass

		public void afterClass() {
		

		}

  
}
