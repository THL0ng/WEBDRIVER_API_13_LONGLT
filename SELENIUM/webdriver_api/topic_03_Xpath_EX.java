package webdriver_api;

import org.testng.annotations.Test;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class topic_03_Xpath_EX {	
		WebDriver driver;
		String firstname = "automation";
		String lastname = "testing";
		String validEmail = "automation" + randomNumber()+"@gmail.com";
		String validPassword = "123123";
		
		public int randomNumber() {
			Random rand= new Random();
			int n =rand.nextInt(50);
			return n;
	}

		@BeforeClass

		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();			
		}

		@Test

		public void TC_01_Loginwithemtyemailandpassword() {
			driver.get("http://live.demoguru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
			Assert.assertEquals(emailErrorMsg,"This is a required field.");
			
			String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg,"This is a required field.");

						
		}

		@Test

		public void TC_02_LoginWithInvalidEmail() {
			driver.get("http://live.demoguru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String emailErrorMsg = driver.findElement(By.xpath(".//div[@id='advice-validate-email-email']")).getText();
			Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
			
			String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg,"This is a required field.");
				
		}

		@Test

		public void TC_03_LoginWithPasswordLess6Char() {
			driver.get("http://live.demoguru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
			driver.findElement(By.xpath("//input[@id= 'pass']")).sendKeys("123");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String passwordErrorMsg= driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg ,"Please enter 6 or more characters without leading or trailing spaces.");
		} 
		
		
		@Test

		public void TC_04_LoginWithIncorrectPassword() {
			driver.get("http://live.demoguru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//input[@id ='email']")).sendKeys("automation@gmail.com");
			driver.findElement(By.xpath("//input[@id= 'pass']")).sendKeys("123789");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String errorMsg= driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
			Assert.assertEquals(errorMsg ,"Invalid login or password.");
		
		}	

		@Test

		public void TC_05_CreateNewAccount() {
			driver.get("http://live.demoguru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			driver.findElement(By.xpath("//span[(text()='Create an Account')]")).click();
			driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
			driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
			driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
			driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
			driver.findElement(By.xpath("//button[@title='Register']")).click();
			
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
			
			Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
			Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, "+firstname+" "+lastname+"!']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),"+firstname+""+lastname+")]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+ validEmail + "')]")).isDisplayed());
			//Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, Automation Testing!']")).isDisplayed());
			
			driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[@class='label']")).click();
			driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		}
		
		@Test

		public void TC_06_LoginWithValidEmailAndPassword() {
			driver.get("http://live.demoguru99.com/");
			
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
			
			driver.findElement(By.xpath("//input[@id ='email']")).sendKeys(validEmail);
			
			driver.findElement(By.xpath("//input[@id= 'pass']")).sendKeys(validPassword);
			
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			// Có 2 cách verify 
			// cách 1 : dùng hàm assertTrue( điều kiện)--> locator dc hiển thị(isDisplayed)
			// --> Dùng khi giá trị KO CỐ ĐỊNH, thay đổi
			
			Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, "+firstname+" "+lastname+"!']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),"+firstname+""+lastname+")]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+ validEmail + "')]")).isDisplayed());	
			// cách 2 : dùng hàm assertEqual(điều kiện 1, điều kiện 2)-->gettext() --actual result,expected result
			// --> Dùng khi giá trị CỐ ĐỊNH
			Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");		
		}
		
		@AfterClass

		public void afterClass() {
			driver.quit();

		}

  
}
