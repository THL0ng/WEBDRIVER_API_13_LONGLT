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

public class topic_05_WebElement {	
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
			driver.get("https://automationfc.github.io/basic-form/index.html");
			// THAO TÁC VỚI 1 ELEMENT ( VD : EMAIL TEXBOX )
			
			// NẾU CHỈ TƯƠNG TÁC LÊN ELEMENT 1 LẦN THÌ KHÔNG CẦN KHAI BÁO BIÉN
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automationfc.vn@gmail.com");
			
			// NẾU TƯƠNG TÁC LÊN ELEMENT NHIỀU LẦN THÌ NÊN KHAI BÁO BIẾN
			WebElement emailtextbox = driver.findElement(By.xpath("//input[@id='mail']"));
			emailtextbox.clear();
			emailtextbox.sendKeys("automationfc.vn@gmail.com");
			Assert.assertTrue(emailtextbox.isDisplayed() );
			
			
			// THAO TÁC VỚI NHIỀU ELEMENT 
			
			// TƯƠNG TÁC LÊN ALL LINKS Ở PAGE HIỆN TẠI
			List <WebElement> links = driver.findElements(By.xpath("//a"));
			
			// CÓ BAO NHIÊU LINKS Ở PAGE HIỆN TẠI
			System.out.println("link size = " + links.size()); 
			
			// GET RA ALL TEXT CỦA LINK
			for (WebElement link : links ) {
				System.out.println("text = " + link.getText());
			}
			
			
			//===================== ===================WEB ELEMENT METHOD (API)=============================================
			
			// XÓA DỮ LIỆU TRONG TEXTBOX/TEXTAREA/DROPDOWN LIST TRƯỚC KHI SENKEYS
			emailtextbox.clear();
			
			// NHẬP DỮ LIỆU VÀO TEXTBOX/TEXTAREA/DROPDOWN LIST
			emailtextbox.sendKeys("");
			
			// CLICK VÀO LINK/BUTTON/CHECKBOX/RADIO BUTTON/ IMAGE / DROPDOWN LIST ( CUSTOM )
			emailtextbox.click();
			
			
			WebElement passwordTextbox = driver.findElement(By.id("password"));
			String passwordTextboxHint = passwordTextbox.getAttribute("placehoder");
			System.out.println(passwordTextboxHint);
			
			
			// GUI: GRAPHIC USER INTERFACE : FONT / SIZE / COLOR / LOCATION / POSSITON ...
			
			WebElement buttonDisabled = driver.findElement(By.id("button-disabled"));
			String buttonBackground = buttonDisabled.getCssValue("background-color");
			String buttonFontSize = buttonDisabled.getCssValue("font-size");
			System.out.println(buttonBackground);
			System.out.println(buttonFontSize);

			
			
			// LẤY RA ĐỘ PHÂN GIẢI CỦA NÓ SO VỚI MÀN HÌNH
			
			buttonDisabled.getLocation();
			
			buttonDisabled.getSize();
			String buttonTagname = buttonDisabled.getTagName();
			System.out.println(buttonTagname);
			
			System.out.println(driver.findElement(By.tagName("h1")).getText());
			
			// KIỂM TRA CHO BẤT KÌ 1 ELEMENT NÀO ĐƯỢC HIỂN THỊ Ở TRÊN PAGE HAY KO
			// DISPLAYED / VISIBLE ( USER : CÓ THỂ NHÌN THẤY VÀ THAO TÁC ĐƯỢC )
			
			WebElement userAvatar5 = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
			Assert.assertFalse(userAvatar5.isDisplayed());
			
			// KIỂM TRA CHO 1 ELEMENT CÓ ĐƯỢC PHÉP THAO TÁC HAY KO ( NÓ CÓ BỊ DISABLE ) 
			Assert.assertFalse(buttonDisabled.isEnabled());
			
			// KIỂM TRA CHO 1 ELEMENT ĐÃ ĐƯỢC CHỌN HAY CHƯA ( RADIO/CHECK BOX )
			WebElement under18Radio = driver.findElement(By.id("under_18"));
			Assert.assertFalse(under18Radio.isSelected());
			under18Radio.click();
			Assert.assertTrue(under18Radio.isSelected());
			
			// WORK CHO CÁI ELEMENT LÀ FORM
			
			//userAvatar5.submit();
					
		}

		

		@AfterClass

		public void afterClass() {
			driver.quit();

		}

  
}
