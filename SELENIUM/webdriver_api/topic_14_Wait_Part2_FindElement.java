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

public class topic_14_Wait_Part2_FindElement {	
		WebDriver driver;
		List <WebElement> elements;

		@BeforeClass

		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", ".\\LIB\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test

		public void TC_01_FindElement() {
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			
			// CASE 1 : KHÔNG TÌM THẤY ELEMENT NÀO HẾT
			//driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("123456");
			// NẾU NHƯ ĐANG TÌM ELEMENT MÀ CHƯA HẾT TIMEOUT-->ELEMENT XUẤT HIỆN THÌ VẪN PASS
			// LUÔN TÌM ELEMENT THEO CHU KÌ 0.5S CHO ĐẾN HẾT TIMEOUT CỦA IMLICITWAIT
			// KẾT QUẢ : FAILED VÀ THROW RA EXCEPTION : NO SUCH ELEMENT
			
			
			// CASE 2 : TÌM THẤY DUY NHẤT 1 ELEMENT (NODE)
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("email@gmail.com");
			
			// CASE 3 : TÌM THẤY NHIỀU HƠN 1 ELEMENT (NODE)--> THAO TÁC VỚI ELEMENT ĐẦU TIÊN
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
		}

		@Test

		public void TC_02_FindElements() {
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			
			// CASE 1 : KHÔNG TÌM THẤY ELEMENT NÀO HẾT
			elements = driver.findElements(By.xpath("//input[@id='id_order']"));
			// NẾU NHƯ ĐANG TÌM ELEMENT MÀ CHƯA HẾT TIMEOUT-->ELEMENT XUẤT HIỆN THÌ VẪN PASS
			// LUÔN TÌM ELEMENT THEO CHU KÌ 0.5S CHO ĐẾN HẾT TIMEOUT CỦA IMLICITWAIT
			// KẾT QUẢ : KO ĐÁNH FAILED TEST CASE VÀ TRẢ VỀ 1 EMPTY LIST
			System.out.println("Size of list" + elements.size());
			Assert.assertTrue(elements.isEmpty());
			Assert.assertEquals(elements.size(), 0);
			
			
			// CASE 2 : TÌM THẤY DUY NHẤT 1 ELEMENT (NODE)
			elements = driver.findElements(By.xpath("//input[@id='email']"));
			System.out.println("Size of list" + elements.size());
			Assert.assertFalse(elements.isEmpty());
			Assert.assertEquals(elements.size(), 1);
			elements.get(0).sendKeys("email@gmail.com");
			// 1| 2 | 3 |... : element
			// 0| 1 | 2 |... : List
			
			
			// CASE 3 : TÌM THẤY NHIỀU HƠN 1 ELEMENT (NODE)
			elements = driver.findElements(By.xpath("//button[@type='submit']"));
			System.out.println("Size of list" + elements.size());
			Assert.assertFalse(elements.isEmpty());
			Assert.assertEquals(elements.size(), 4);
			
		
		}

		@Test

		public void TC_03_() {
			
		}

		@AfterClass

		public void afterClass() {
			

		}

  
}
