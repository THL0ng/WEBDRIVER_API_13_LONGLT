package webdriver_api;

import org.testng.annotations.Test;

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

public class topic_07_DropdownList {	
		WebDriver driver;
		Select select;

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		public void TC_01_() throws InterruptedException {
			driver.get("https://egov.danang.gov.vn/reg");
			
			// THAO TÁC VỚI CITY DROPDOWN LIST
			select = new Select(driver.findElement(By.name("tinhThuongTru")));
			
		
			// KIỂM TRA DROPDOWN NÀY KO ĐƯỢC PHÉP CHỌN NHIỀU ( DC PHÉP THÌ DÙNG TRUE )
			boolean citydropdown = select.isMultiple();
			System.out.println("city status = " + citydropdown );
			Assert.assertFalse(citydropdown);
			
			/*<option id="Chọn" value="">Tỉnh/TP</option> -> 0
			<option value="11433">thành phố Cần Thơ</option> -> 1
			<option value="1">thành phố Hà Nội</option> -> 2
			<option value="4091">thành phố Hải Phòng</option> -> 3
			<option value="9806">thành phố Hồ Chí Minh</option> -> 4
			<option selected="" value="7237">thành phố Đà Nẵng</option> -> 5
			<option value="11104">tỉnh An Giang</option> -> 6
			<option value="9715">tỉnh Bà Rịa - Vũng Tàu</option> -> 7
			<option value="9433">tỉnh Bình Dương</option> -> 8 */
			
			
			select.selectByIndex(2);
			Thread.sleep(3000);
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hà Nội");
			
			select.selectByValue("9806");
			Thread.sleep(3000);
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");


			
			select.selectByVisibleText("thành phố Đà Nẵng");
			Thread.sleep(3000);
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Đà Nẵng");

			
			// SAU KHI THÀNH CÔNG , LÀM SAO KIỂM TRA ĐỂ BIẾT NÓ CHỌN ĐÚNG
			// DÙNG HÀM select.getFirstSelectedOption().getText()
			
			
			
			// LÀM SAO BIẾT TRONG DROPDOWN LIST CÓ BAO NHIÊU LỰA CHỌN
			int cityNumber = select.getOptions().size();
			System.out.println("All tỉnh thành =  " + cityNumber);
			Assert.assertEquals(cityNumber, 65); // ko dùng "65" vì cityNumber đang dùng kiểu int
			
			
			// IN RA TẤT CẢ GIÁ TRỊ NẰM TRONG DROPDOWN ---> ĐỂ XEM TẤT CẢ GIÁ TRỊ TRONG DROPDOWN LIST NÀY SORT CÓ ĐÚNG HAY KO
			List <WebElement> Alloptions = select.getOptions();
			for (WebElement option : Alloptions ) {
				System.out.println(option.getText());
			}
			
			

			

			
			
		}

		@Test

		public void TC_02_() {
					
		}

		@Test

		public void TC_03_() {
			
		}

		@AfterClass

		public void afterClass() {
			

		}

  
}
