package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_07_CustomDropDownList {	
		WebDriver driver;
		WebDriverWait waitexplicit;
		JavascriptExecutor javascrip;
		Actions action;
		By numberAllItems = By.xpath("//ul[@id='number-menu']//li");

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			waitexplicit = new WebDriverWait(driver, 10);
			javascrip = (JavascriptExecutor) driver; 
			action = new Actions(driver);
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
		
		public String gettextElement(String Xpathlocator) {
			WebElement element = driver.findElement(By.xpath(Xpathlocator));
			return element.getText();
		}
		
		
		

		@Test

		public void TC_02_Angular() throws InterruptedException {
			driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
			
			//CLICK VÀO FOOTBALL VÀ KIỂM TRA NÓ ĐƯỢC CHỌN THÀNH CÔNG
			selectItemInCustomDropDown("//ejs-dropdownlist[@id='games']" , "//ul[@id='games_options']//li", "Football");
			Thread.sleep(2000);
			
			// KIỂM TRA FOOTBALL ĐƯỢC CHỌN THÀNH CÔNG
			String expectedValue = getTextByJS("#games_hidden > option");
			System.out.println("Text = " + expectedValue);
			Assert.assertEquals(expectedValue, "Football"); 
			
		}
		
		public String getTextByJS(String locator) {
			return (String) javascrip.executeScript("return document.querySelector('" + locator + "').text");
		}

		@Test

		public void TC_03_ReactJS() throws InterruptedException {
			driver.get("https://react.semantic-ui.com/modules/dropdown/");
			
			// CLICK VÀO CHRISTIAN VÀ KIỂM TRA NÓ ĐƯỢC CHỌN THÀNH CÔNG
			selectItemInCustomDropDown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i" , "//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span", "Christian");
			
			// VERIFY ITEM ĐÃ ĐƯỢC CHỌN THÀNH CÔNG
			Assert.assertTrue(isElementDisplayed ("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='text' and text()='Christian']"));
			Thread.sleep(2000);
			
			
			// CLICK VÀO Jenny Hess VÀ KIỂM TRA NÓ ĐƯỢC CHỌN THÀNH CÔNG
			selectItemInCustomDropDown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i" , "//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']/span", "Jenny Hess");
			Assert.assertTrue(isElementDisplayed ("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='text' and text()='Jenny Hess']"));
			Thread.sleep(2000);
		
		}
		
		@Test

		public void TC_04_Editable() throws InterruptedException {
			driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
			
			inputItemInCustomDropDown("//div[contains(@class,'search selection')]//i[@class='dropdown icon']" , "//input[@class='search']" , "American Samoa");
			Assert.assertTrue(isElementDisplayed ("//div[contains(@class,'search selection')]/div[@class='text' and text()='American Samoa']")); 
			Thread.sleep(2000);
			
			
		
		}
		
		
		public void inputItemInCustomDropDown(String parenxpath , String inputxpath , String expectedtext) {
			// 1-CLICK VÀO DROPDOWN ĐỂ XỔ RA HẾT TẤT CẢ ITEM
			  driver.findElement(By.xpath(parenxpath)).click();
			  
			// 2-INPUT TEXT VÀO TEXTBOX
			  driver.findElement(By.xpath(inputxpath)).sendKeys(expectedtext);

			// 3-TRUYỀN PHÍM ENTER VÀO INPUT TEXT
			  action.sendKeys(driver.findElement(By.xpath(inputxpath)), Keys.ENTER).perform();;
			
		}
		
		

		@AfterClass

		public void afterClass() {
		

		}

  
}
