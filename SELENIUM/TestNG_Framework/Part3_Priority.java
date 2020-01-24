package TestNG_Framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part3_Priority {
  @Test
  public void  Priority_01_Create_new_customer() {
	  System.out.println("Run TC 01");
  }
  
  
  @Test
  public void Priority_02_Edit_customer() {
	  System.out.println("Run TC 02");
  }
  
  @Test(enabled = false) //Skip TC or xóa luôn @test cũng sẽ skip
  public void Priority_03_Create_new_account() {
	  System.out.println("Run TC 03");
  }
  
  @Test(description = " VIẾT CÁI GÌ ĐÓ DÀI DÀI VÀO ĐÂY MÀ KO CẦN THEO CÚ PHÁP ĐẶT TÊN CŨNG ĐƯỢC")
  public void Priority_04_Edit_account() {
	  System.out.println("Run TC 04");
  }
  
  
  @Test
  public void Priority_05_Delete_account() {
	  System.out.println("Run TC 05");
  }
  
  @Test
  public void Priority_06_Delete_customer() {
	  System.out.println("Run TC 06");
  }

}
