package webdriver_api;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_09_User_Interactions {	
		WebDriver driver;
		Actions action;
		
		JavascriptExecutor javascriptexecutor;
		String javascriptPath , jqueryPath;

		@BeforeClass

		public void beforeClass() {
		/*	FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.webnotification.enabled" , false);
			driver = new FirefoxDriver(profile);*/
			String projectpath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", projectpath + "\\LIB\\chromedriver.exe");
			driver = new  ChromeDriver();
			action = new Actions(driver);
			
			javascriptexecutor = (JavascriptExecutor) driver;
			
			javascriptPath = projectpath + "\\DrapAndDrop\\drag_and_drop_helper.js";
			jqueryPath = projectpath + "\\DrapAndDrop\\jquery_load_helper.js";
					
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
		
		@Test
		public void TC_05_RightClick() throws InterruptedException { // DÙNG TRÌNH DUYỆT CHROME 
			driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
			
			action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
			Thread.sleep(3000);
			
			action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
			Thread.sleep(3000);

			
			Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());
			
			action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
			Thread.sleep(3000);

			
			Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
			
			driver.switchTo().alert().accept();
			
		}
		
		@Test
		public void TC_06_DrapAndDrop() throws InterruptedException {
			driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
			
			WebElement sourceCirle = driver.findElement(By.xpath("//div[@id='draggable']"));
			WebElement targetCirle = driver.findElement(By.xpath("//div[@id='droptarget']"));
			
			action.dragAndDrop(sourceCirle, targetCirle).perform();
			Thread.sleep(3000);
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());

		
		} 
		
		
		@Test
		public void TC_07_DrapAndDrop_HTML5() throws InterruptedException , IOException{
			driver.get("http://the-internet.herokuapp.com/drag_and_drop");
			
			String sourceCss = "#column-a";
			String targetCss = "#column-b";

			String java_script = readFile(javascriptPath);

			// Inject Jquery lib to site
			// String jqueryscript = readFile(jqueryPath);
			// javascriptExecutor.executeScript(jqueryscript);

			// A to B
			java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
			javascriptexecutor.executeScript(java_script);
			Thread.sleep(3000);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

			// B to A
			javascriptexecutor.executeScript(java_script);
			Thread.sleep(3000);
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

			
			
			
			
		}
		
		public String readFile(String filePath) throws IOException {
			Charset cs = Charset.forName("UTF-8");
			FileInputStream stream = new FileInputStream(filePath);
			try {
				Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
				StringBuilder builder = new StringBuilder();
				char[] buffer = new char[8192];
				int read;
				while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
					builder.append(buffer, 0, read);
				}
				return builder.toString();
			} finally {
				stream.close();
			}
		}
		
		


		@AfterClass

		public void afterClass() {
		

		}

  
}
