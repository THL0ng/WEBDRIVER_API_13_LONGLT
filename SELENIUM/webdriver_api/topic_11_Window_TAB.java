package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;

public class topic_11_Window_TAB {	
		WebDriver driver;
		JavascriptExecutor javascriptexecutor;
		WebElement element;

		@BeforeClass

		public void beforeClass() {
			/*FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("dom.webnotification.enabled" , false);
			driver = new FirefoxDriver(profile);*/
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			javascriptexecutor = (JavascriptExecutor) driver;

			

		}

		@Test

		public void TC_01_Windows() throws InterruptedException {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			String parentID = driver.getWindowHandle();
			System.out.println("parent Id = " + parentID);
			
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			
			switchToWindowsbyID(parentID);
			
			Assert.assertEquals(driver.getTitle(), "Google");
			Thread.sleep(2000);
			// BACK TRỞ LẠI MAIN PAGE
			driver.switchTo().window(parentID);
			
			driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
			
			// DÙNG TITLE VÌ LÚC NÀY ĐÃ MỞ 3 TAB ( CÓ THỂ DÙNG TITLE NGAY TỪ ĐẦU CŨNG DC )
			switchToWindowsbyTitle("Facebook - Đăng nhập hoặc đăng ký");

			Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
			Thread.sleep(2000);
			
			// CÓ THỂ DÙNG TIẾP driver.switchTo().window(parentID); ĐỂ BACK VỀ MAIN PAGE HOẶC DÙNG : 
			switchToWindowsbyTitle("SELENIUM WEBDRIVER FORM DEMO");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

			
			driver.findElement(By.xpath("//a[text()='TIKI']")).click();
			
			switchToWindowsbyTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

			Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
			Thread.sleep(2000);
			
			CloseAllWindowsWithoutParent(parentID); 
	
		} 
		
		// SWITCH TO CHILD WINDOWS ( ONLY 2 WINDOWS )
		public void switchToWindowsbyID (String parentID) {
			//LẤY RA TẤT CẢ ID ĐANG CÓ
			Set<String> allWindows = driver.getWindowHandles();
			// DÙNG VÒNG LẶP FOR ĐỂ DUYỆT QUA TỪNG ID (FOR-EACH)
			for (String TermID:allWindows) {
			// KIỂM TRA ID NÀO KHÁC VỚI PARENTID THÌ SWITCH QUA ( dấu ! là phủ định lại điều kiện )
				if(!TermID.equals(parentID)) {
			// SWITCH QUA TAB/WINDOWS ĐÓ
					driver.switchTo().window(TermID);
					break;
				}
			}
			
		}
		
		
		//SWITCH TO CHILD WINDOWS ( GREATER THAN 2 WINDOWS AND TITLE OF THE PAGES ARE UNIQUE )
		public void switchToWindowsbyTitle (String title) {
			//LẤY RA TẤT CẢ ID ĐANG CÓ
			Set<String> allWindows = driver.getWindowHandles();
			System.out.println("all Windows size = " + allWindows.size() );
			// DÙNG VÒNG LẶP FOR ĐỂ DUYỆT QUA TỪNG ID (FOR-EACH)
			for (String TermID:allWindows) {
				System.out.println("ID = " + TermID);
				//SWITCH VÀO TỪNG ID TRƯỚC
				driver.switchTo().window(TermID);
				// GET RA TITLE ĐANG CÓ
				String currentWin= driver.getTitle();
				System.out.println("Title= " + currentWin);
				// KIỂM TRA TITLE NÀO = VỚI TITLE MÌNH EXPECTED THÌ BREAK KHỎI VÒNG LẶP
				if(currentWin.equals(title)) {
					break;
				}
			}

			
		}
		
		
		//CLOSE ALL WINDOWS WITHOUT PARENT WINDOWS
		public boolean CloseAllWindowsWithoutParent(String parentID) {
			//LẤY RA TẤT CẢ ID ĐANG CÓ
			Set<String> allWindows = driver.getWindowHandles();
			// DÙNG VÒNG LẶP FOR ĐỂ DUYỆT QUA TỪNG ID
			for (String runWindows : allWindows) {
				// KIỂM TRA ID NÀO KHÁC VỚI PARENTID THÌ SWITCH QUA
				if(!runWindows.equals(parentID)) {
					//SWITCH VÀO WINDOWS ĐÓ
					driver.switchTo().window(runWindows);
					// CLOSE
					driver.close();
				}
			}
			driver.switchTo().window(parentID);
			if (driver.getWindowHandles().size() == 1)
				return true;
			else
				return false;
						
		} 
		

		@Test

		public void TC_02_Windows_Tab2() throws InterruptedException {
			driver.get("https://kyna.vn/");
			Thread.sleep(5000);
			
			String parentID = driver.getWindowHandle();
			System.out.println("parent Id = " + parentID);
			
			System.out.println("Step 02 - Count popup number");
			List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
			System.out.println("Fancy popup number = " + fancyPopup.size() );
			
			if(fancyPopup.size() > 0 ) {
				System.out.println("Step 3 - Check popup displayed and close it");
				Assert.assertTrue(fancyPopup.get(0).isDisplayed());
				driver.findElement(By.cssSelector(".fancybox-close")).click();
			}
			
			clickToElementByJS("//img[@alt='facebook']");
			switchToWindowsbyTitle("Kyna.vn - Trang chủ | Facebook");
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
			
			clickToElementByJS("//img[@alt='youtube']");
			switchToWindowsbyTitle("Kyna.vn - YouTube");
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
			
			clickToElementByJS("//img[@alt='zalo']");
			switchToWindowsbyTitle("Kyna.vn");
			Assert.assertEquals(driver.getTitle(), "Kyna.vn");
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
			
	/*		// TRƯỜNG HỢP NÀY CẢ EXPECTD RESULT VÀ ACTUAL RESULT ĐỀU GIỐNG NHAU NHƯNG THẬT RA KHÁC VỀ KIỂU CHỮ ( DÙNG DIFFCHECKER CHECK SẼ THẤY )
	 		// THẦY KIỂM TRA LẠI SAU
			clickToElementByJS("//img[@alt='apple-app-icon']");
			switchToWindowsbyTitle("‎KYNA on the App Store");
			String kynaAppTitle = driver.getTitle();
			System.out.println(kynaAppTitle); 
			Assert.assertEquals(kynaAppTitle,"KYNA on the App Store"); 
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia"); */
			
			clickToElementByJS("//img[@alt='android-app-icon']");
			switchToWindowsbyTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
			Assert.assertEquals(driver.getTitle(), "KYNA - Học online cùng chuyên gia - Apps on Google Play");
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
			
			clickToElementByJS("//img[@alt='kynaforkids.vn']");
			switchToWindowsbyTitle("Kynaforkids.vn trường học trực tuyến cho trẻ");
			Assert.assertEquals(driver.getTitle(), "Kynaforkids.vn trường học trực tuyến cho trẻ");
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
			
			clickToElementByJS("//img[@alt='kynabiz.vn']");
			switchToWindowsbyTitle("Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
			Assert.assertEquals(driver.getTitle(), "Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
			Thread.sleep(2000);
			switchToWindowsbyTitle("Kyna.vn - Học online cùng chuyên gia");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
			
			CloseAllWindowsWithoutParent(parentID); 
				
		}
		
		public void clickToElementByJS(String locator) {
			element= driver.findElement(By.xpath(locator));
			javascriptexecutor.executeScript("arguments[0].click();", element);
		}

		@Test

		public void TC_03_Window_Tab3() throws InterruptedException {
			driver.get("http://live.demoguru99.com/index.php/");
						
			driver.findElement(By.xpath("//a[text()='Mobile']")).click();
			
			driver.findElement(By.xpath("//a[@title='Xperia']//following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
			
			driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div[@class='product-info']//a[text()='Add to Compare']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
			
			driver.findElement(By.xpath("//button[@title='Compare']")).click();
			Thread.sleep(2000);
			
			switchToWindowsbyTitle("Products Comparison List - Magento Commerce");
			Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
			
			driver.findElement(By.xpath("//button[@class='button']")).click();
			Thread.sleep(2000);

			
			switchToWindowsbyTitle("Mobile");
	
			driver.findElement(By.xpath("//a[text()='Clear All']")).click();
			Thread.sleep(2000);

			
			driver.switchTo().alert().accept();
			
			Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		
		}

		@AfterClass

		public void afterClass() {
			

		}

  
}
