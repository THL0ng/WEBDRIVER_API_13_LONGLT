package webdriver_api;

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_13_UpLoadFile {	
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");	
		String pic1 = projectPath + "\\uploadFile\\pic1.jpg";
		String pic2 = projectPath + "\\uploadFile\\pic2.jpg";
		String pic3 = projectPath + "\\uploadFile\\pic3.jpg";
		String pic4 = projectPath + "\\uploadFile\\pic4.jpg";
		
		String ChromeAutoIt = projectPath + "\\uploadAutoIT\\chrome.exe";
		String FirefoxAutoIT = projectPath + "\\\\uploadAutoIT\\firefox.exe";


		@BeforeClass

		public void beforeClass() {
			// CHẠY TRÊN FIREFOX MỚI NHẤT
			//System.setProperty("webdriver.gecko.driver", ".\\LIB\\geckodriver.exe");
			//driver = new FirefoxDriver();
			
			// CHẠY TRÊN CHROME
			System.setProperty("webdriver.chrome.driver", ".\\LIB\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
	
		}

		@Test

		public void TC_01_Senkeys() throws InterruptedException {
			driver.get("http://blueimp.github.io/jQuery-File-Upload/");
				
			WebElement uploadFile = find("//input[@type='file']");
			
			// UPLOAD 1 FILE
			//uploadFile.sendKeys(pic1);
			//Thread.sleep(3000);
			//Assert.assertTrue(find("//p[@class='name']//a[@title='pic1.jpg']").isDisplayed());

			
			//UPLOAD NHIỀU FILES ( THÊM "\n" ) ( UPLOAD NHIỀU FILE CHỈ CHẠY TRÊN FIREFOX VER 55 TRỞ LÊN + SELENIUM 3.XX )
			uploadFile.sendKeys(pic1 + "\n" + pic2 + "\n" + pic3 + "\n" + pic4);
			Thread.sleep(2000);
			
			List <WebElement> startbutton = finds("//table//button[@class='btn btn-primary start']");
			for (WebElement start : startbutton ) {
				start.click();
				Thread.sleep(2000);
			}
			
			Assert.assertTrue(find("//p[@class='name']//a[@title='pic1.jpg']").isDisplayed());
			Assert.assertTrue(find("//p[@class='name']//a[@title='pic2.jpg']").isDisplayed());
			Assert.assertTrue(find("//p[@class='name']//a[@title='pic3.jpg']").isDisplayed());
			Assert.assertTrue(find("//p[@class='name']//a[@title='pic4.jpg']").isDisplayed());

		
		}
		
		// VIẾT GỌN DRIVER.FINDELEMENT
		
		public WebElement find(String xpath) {
			return driver.findElement(By.xpath(xpath));
		}
		
		public List <WebElement> finds(String xpath){
			return driver.findElements(By.xpath(xpath));	
		}
		

		@Test

		public void TC_02_AutoIT() throws IOException, InterruptedException {
			driver.get("http://blueimp.github.io/jQuery-File-Upload/");
			
			WebElement uploadfile = driver.findElement(By.cssSelector(".fileinput-button"));
			uploadfile.click();
			Thread.sleep(2000);
			
			// EXECUTE RUNTIME FILE (.EXE , .BAT , .MSI , .SH)
			// CÓ THỂ VIẾT : Runtime.getRuntime().exec(new String[] {ChromeAutoIt ,pic1}); OR
			
			if (driver.toString().contains("firefox")) {
				Runtime.getRuntime().exec(new String[] {FirefoxAutoIT ,pic1}); 
			} else {
				Runtime.getRuntime().exec(new String[] {ChromeAutoIt ,pic1});
			}
				
			
			find("//table//button[@class='btn btn-primary start']").click();
			Thread.sleep(2000);
			
			Assert.assertTrue(find("//p[@class='name']//a[@title='pic1.jpg']").isDisplayed());
			
		}

		@Test

		public void TC_03_Robot()throws IOException, InterruptedException, AWTException {
			driver.get("http://blueimp.github.io/jQuery-File-Upload/");
			
			WebElement uploadfile = driver.findElement(By.cssSelector(".fileinput-button"));
			uploadfile.click();
			Thread.sleep(2000);
			
			StringSelection select = new StringSelection(pic1);
			
			//COPY TO CLIPBOARD
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			
			Robot robot = new Robot();
			Thread.sleep(1000);
			
			//NHẤN PHÍM ENTER
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			// NHẤN XUỐNG CTRL-V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			
			//NHẢ CTRL-V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			
			//NHẤN ENTER
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);


			
			
			
		}

		@AfterClass

		public void afterClass() {

		}

  
}
