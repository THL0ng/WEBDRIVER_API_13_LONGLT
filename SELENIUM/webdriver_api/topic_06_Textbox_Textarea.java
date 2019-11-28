package webdriver_api;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class topic_06_Textbox_Textarea {	
		WebDriver driver;
		String username = "mngr233468"; //khai báo biến trên đây thì khi username or pass thay đổi ,tất cả username or pass bên dưới tự động đổi,ko cần sửa từng chỗ
		String password = "tYqAhaq";
		String customerID;
		
		// input TRONG NEW CUSTOMER / output (server) data 
		String customerName = "ShenLong";
		String gender = "male";
		String DateofBirth = "2000-10-10";
		String Address = "221B Baker";
		String City = "london";
		String State = "USA";
		String Pin = "555000";
		String Phone = "0901112220";
		String Email = "Shenlong" + randomNumber()+"@gmail.com";
		
		public int randomNumber() {
			Random rand= new Random();
			int n =rand.nextInt(5000);
			return n;
	}
		
		
		// INPUT TRONG EDIT CUSTOMER
		String editAddress = "16A LHP";
		String editCity = "HCM";
		String editState = "VN";
		String editPin = "096555";
		String editPhone = "0901233210";
		String editEmail = "Shenlong" + randomNumber()+"@hotgmail.com";
		
		
		
		
		// LOCATOR FOR NEW/EDIT FORM
		By nametextbox = By.name("name");
		By genderradio = By.xpath("//input[@value='m']");
		By genderTextbox = By.name("gender");
		By DOBtextbox = By.name("dob");
		By addressTextarea = By.name("addr");
		By citytextbox = By.name("city");
		By statetextbox = By.name("state");
		By pintextbox = By.name("pinno");
		By phonetextbox = By.name("telephoneno");
		By Emailtextbox = By.name("emailid");
		By passwordtextbox = By.name("password");
		By submitbutton = By.name("sub");

		
		

		@BeforeClass

		public void beforeClass()  {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://demo.guru99.com/v4/");
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username); // khai báo biến nên ko sử dụng ""
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password); // khai báo biến nên ko sử dụng ""
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			String homepagewelcomemsg = driver.findElement(By.tagName("marquee")).getText();
			Assert.assertEquals(homepagewelcomemsg, "Welcome To Manager's Page of Guru99 Bank" );
			
				
			Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "']")).isDisplayed());
			
		}

		@Test

		public void TC_01_Newcustomer() {
			driver.findElement(By.xpath("//a[text()='New Customer']")).click();
			
			// INPUT DATA TO NEW CUSTOMER FORM
			driver.findElement(nametextbox).sendKeys(customerName);
			driver.findElement(genderradio).click();
			driver.findElement(DOBtextbox).sendKeys(DateofBirth);
			driver.findElement(addressTextarea).sendKeys(Address);
			driver.findElement(citytextbox).sendKeys(City);
			driver.findElement(statetextbox).sendKeys(State);
			driver.findElement(pintextbox).sendKeys(Pin);
			driver.findElement(phonetextbox).sendKeys(Phone);
			driver.findElement(Emailtextbox).sendKeys(Email);
			driver.findElement(passwordtextbox).sendKeys(password);
			driver.findElement(submitbutton).click();
			
			
			Assert.assertTrue(driver.findElement(By.xpath("//td[@colspan='2']//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
			
			// VERIFY OUTPUT DATA MATCH VỚI INPUT 
			Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
			Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
			Assert.assertEquals(DateofBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
			Assert.assertEquals(Address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
			Assert.assertEquals(City, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
			Assert.assertEquals(State, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
			Assert.assertEquals(Pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
			Assert.assertEquals(Phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
			Assert.assertEquals(Email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
			
			 customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
			
		
		}

		@Test

		public void TC_02_EditCustomer() {
			driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
			driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
			driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
			
			// VERIFY NAME/GENDER/ DOB IS DISABLED FIELDS
			Assert.assertFalse(driver.findElement(nametextbox).isEnabled());
			Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
			Assert.assertFalse(driver.findElement(DOBtextbox).isEnabled());
			
			// VERIFY OUTPUT TẠI EDIT FORM VỚI INPUT CỦA NEW CUSTOMER FORM
			Assert.assertEquals(customerName, driver.findElement(nametextbox).getAttribute("value"));
			Assert.assertEquals(gender, driver.findElement(genderTextbox).getAttribute("value"));
			Assert.assertEquals(DateofBirth, driver.findElement(DOBtextbox).getAttribute("value"));
			Assert.assertEquals(Address, driver.findElement(addressTextarea).getText());
			Assert.assertEquals(City, driver.findElement(citytextbox).getAttribute("value"));
			Assert.assertEquals(State, driver.findElement(statetextbox).getAttribute("value"));
			Assert.assertEquals(Pin, driver.findElement(pintextbox).getAttribute("value"));
			Assert.assertEquals(Phone, driver.findElement(phonetextbox).getAttribute("value"));
			Assert.assertEquals(Email, driver.findElement(Emailtextbox).getAttribute("value"));
			
			
			// EDIT DATA TẠI EDIT CUSTOMER FORM
			
			driver.findElement(addressTextarea).clear();
			driver.findElement(addressTextarea).sendKeys(editAddress);
			driver.findElement(citytextbox).clear();
			driver.findElement(citytextbox).sendKeys(editCity);
			driver.findElement(statetextbox).clear();
			driver.findElement(statetextbox).sendKeys(editState);
			driver.findElement(addressTextarea).clear();
			driver.findElement(addressTextarea).sendKeys(editAddress);
			driver.findElement(pintextbox).clear();
			driver.findElement(pintextbox).sendKeys(editPin);
			driver.findElement(phonetextbox).clear();
			driver.findElement(phonetextbox).sendKeys(editPhone);
			driver.findElement(Emailtextbox).clear();
			driver.findElement(Emailtextbox).sendKeys(editEmail);
			driver.findElement(submitbutton).click();
			
			
			Assert.assertTrue(driver.findElement(By.xpath("//td[@colspan='2']//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
			
			// VERIFY OUTPUT DATA MATCH VỚI INPUT 
			Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText());
			Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
			Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
			Assert.assertEquals(DateofBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
			
			Assert.assertEquals(editAddress, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
			Assert.assertEquals(editCity, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
			Assert.assertEquals(editState, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
			Assert.assertEquals(editPin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
			Assert.assertEquals(editPhone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
			Assert.assertEquals(editEmail, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());

			
		}

		
		@AfterClass

		public void afterClass() {
		
		
		}

  
}
