package TestNG_Framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part7_Dependencies {
  @Test
  public void  Priority_01_Create_new_customer() {
	  System.out.println("Run TC 01");
  }
  
  // DependsonMethods : case này phụ thuộc case kia, nếu 1 case failed, các case dưới sẽ skip,ko chạy. đỡ tốn time
  @Test(dependsOnMethods = "Priority_01_Create_new_customer")
  public void Priority_02_Edit_customer() {
	  System.out.println("Run TC 02");
  }
  
  @Test(dependsOnMethods = "Priority_02_Edit_customer")
  public void Priority_03_Create_new_account() {
	  System.out.println("Run TC 03");
	  System.out.println("Run TC 03");
	  Assert.assertTrue(false);
  }
  
  @Test(dependsOnMethods = "Priority_03_Create_new_account")
  public void Priority_04_Edit_account() {
	  System.out.println("Run TC 04");
  }
  
  
  @Test(dependsOnMethods = "Priority_04_Edit_account")
  public void Priority_05_Delete_account() {
	  System.out.println("Run TC 05");
  }
  
  @Test(dependsOnMethods = " Priority_05_Delete_account")
  public void Priority_06_Delete_customer() {
	  System.out.println("Run TC 06");
  }

}
