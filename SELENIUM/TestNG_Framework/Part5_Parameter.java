package TestNG_Framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class Part5_Parameter {
  WebDriver driver;
  By emailTextbox =By.xpath("//*[@id='email']");
  By passwordTextbox = By.xpath("//*[@id='pass']");
  By loginButton = By.xpath("//*[@id='send2']");
  
  @Parameters("browser") // KHỞI TẠO BROWSER BÊN RUNTESTCASES.XML
  @BeforeClass
  public void beforeClass(String browserName) {
	 if (browserName.equals("firefox")) {
	 System.setProperty("webdriver.gecko.driver", ".\\LIB\\geckodriver.exe");
	 driver = new FirefoxDriver();	  
	 } else if(browserName.equals("chrome")) {
	  System.setProperty("webdriver.chrome.driver", ".\\LIB\\chromedriver.exe");
	  driver = new ChromeDriver();	  
	 } else if(browserName.equals("safari")) {
	  
	 } else if(browserName.equals("opera")) {
	  
	 }
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

  }
  
  @Test(dataProvider = "user_pass")
  public void TC_01_LoginToSystem(String username, String password) throws InterruptedException {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(emailTextbox).sendKeys(username);
	  driver.findElement(passwordTextbox).sendKeys(password);
	  driver.findElement(loginButton).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
  }
  
  @DataProvider(name = "user_pass")
  public Object[] [] UserAndPassData() {
	  return new Object[][] { 
		  {"selenium_11_01@gmail.com", "111111"},
		  {"selenium_11_02@gmail.com", "111111"}};
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();	
	}

}
