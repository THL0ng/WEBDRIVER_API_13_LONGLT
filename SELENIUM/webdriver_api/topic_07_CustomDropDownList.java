package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_07_CustomDropDownList {	
		WebDriver driver;
		WebDriverWait waitexplicit;
		By numberAllItems = By.xpath("//ul[@id='number-menu']//li");

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			waitexplicit = new WebDriverWait(driver, 10);
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			

		}

		@Test

		 public void TC_01_Jquery() throws InterruptedException {
			driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
			
			 // Click VÀO 5 VÀ KIỂM TRA ITEM ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
			selectItemInCustomDropDown("//span[@id='number-button']" , "//ul[@id='number-menu']//li", "5");
					
			boolean status = isElementDisplayed ("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']");
			Assert.assertTrue(status);
			Thread.sleep(3000);
		    //CÓ THỂ VIẾT NGẮN GỌN HƠN : Assert.assertTrue(isElementDisplayed ("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));
			
			//CHỌN 10 VÀ KIỂM TRA
			selectItemInCustomDropDown("//span[@id='number-button']" , "//ul[@id='number-menu']//li", "10");
			Assert.assertTrue(isElementDisplayed ("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']"));

			
		} 
		
		
		// CÓ THẺ TÓM GỌN STEP 1-5 BẰNG HÀM DÙNG CHUNG 
		
		// 3 tham số
		//span[@id='number-button'] = parenXpath 
		//ul[@id='number-menu']//li = AllitemsXpath
		// 6 = expectText
		
		public void selectItemInCustomDropDown(String parenxpath , String AllitemsXpath , String expectText) {
			// 1-CLICK VÀO DROPDOWN ĐỂ XỔ RA HẾT TẤT CẢ ITEM
			  driver.findElement(By.xpath(parenxpath)).click();
							
		    // 2-KHAI BÁO 1 LIST WEBELEMENT CHỨA ALL ITEM BÊN TRONG
			  List <WebElement> Allitems = driver.findElements(By.xpath(AllitemsXpath));		
							
			// 3-WAIT CHO ALL ITEM ĐƯỢC XUẤT HIỆN
			  waitexplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(AllitemsXpath)));
							
		    // 4-GET TEXT TỪNG ITEM SAU ĐÓ SO SÁNH VS ITEM MÌNH CẦN CHỌN
			  for (WebElement item: Allitems) {
				   System.out.println(item.getText());
								
	        // 5-KIỂM TRA ITEM NÀO ĐÚNG VS CÁI MÌNH CẦN CHỌN THÌ CLICK VÀO

				if(item.getText().equals(expectText)) {
					item.click();  
					break;  // dùng break thì khi chọn đến item cần chọn nó sẽ ngưng ko chọn nữa
				  }
								
		     	}	
		
		}
		
		
		public boolean isElementDisplayed(String Xpathlocator) {
			WebElement element = driver.findElement(By.xpath(Xpathlocator));
			if (element.isDisplayed()) {
				return true;
			} else {
				return false;
			}
			
		}
		

		@Test

		public void TC_02_Angular() {
			driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
			
			
		}

		@Test

		public void TC_03_() {
			driver.get("");
		}

		@AfterClass

		public void afterClass() {
		

		}

  
}
