package webdriver_api;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class topic_14_Wait_Part6_Fluent_Wait {
    WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebElement> fluentWait;
	By today = By.xpath("//a[text()='16']");
	By selectedDay= By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");
	By todaySelected= By.xpath("//a[text()='16']/parent::td[@class='rcSelected']");
	By loadingIcon= By.xpath("//div[@class='raDiv']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\LIB\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
			
	}


	@Test
	public void TC_06_FluentWait() {
		explicitWait = new WebDriverWait(driver,45);

		
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countDown= driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		explicitWait.until(ExpectedConditions.visibilityOf(countDown));
		
		fluentWait = new FluentWait<WebElement>(countDown);
		
		//total time wait 15s
		fluentWait.withTimeout(15, TimeUnit.SECONDS)
		// every 1s check on 1 time
		.pollingEvery(1, TimeUnit.SECONDS)
		
		//if got exception will be ignored
		.ignoring(NoSuchElementException.class)
		.until(new Function <WebElement, Boolean >(){
			public Boolean apply(WebElement element) {
				
				//check condition countdown = 02
				boolean flag = element.getText().endsWith("02");
				System.out.println("Time = " + element.getText());
				//return value to function apply
				return flag;
			}
		});
	}


		@Test

		public void TC_03_() {
			
		}

		@AfterClass

		public void afterClass() {
			

		}

  
}
