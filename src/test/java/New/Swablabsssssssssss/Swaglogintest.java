package New.Swablabsssssssssss;

import org.testng.annotations.Test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
@Listeners(ExtentTestNGITestListener.class)

public class Swaglogintest {
	
	WebDriver driver;
	swaglogin sl;
	swaghome sh;
	ExtentReports extent;
	ExtentTest test;
	
	
	
	
	 @BeforeMethod(alwaysRun=true)
	  public void beforeMethod() throws InterruptedException {
		 
		 driver=new ChromeDriver();
		 sl=new swaglogin(driver);
		 sl.get("https://www.saucedemo.com/v1/index.html");
		 Thread.sleep(3000);
		 sh=new swaghome(driver);
         
		
		 
		 
	  }
	 
	 
  @Test(priority=1)
  public void validlogin() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
	  sl.login("standard_user", "secret_sauce");
	  Thread.sleep(3000);
	 
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  System.out.println("user logged in");
	  test.log(Status.INFO, "User logged in successfully"); //to know status pass fail
	  sh.menu();
	  sh.logout();
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"),"not displayed");
	  System.out.println("correct page displayed");
	  
	  
	
	
  }
  
  @Test(priority=2)
  public void invalidusername() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();
	  sl.login("standard_user1", "secret_sauce");
	  Thread.sleep(3000);
	  
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  
	  System.out.println("access denied");
	  test.log(Status.INFO, "Access denied");

	  Assert.assertTrue(sl.error().equals("Epic sadface: Username and password do not match any user in this service"),"Wrong message displayed");
	  System.out.println("correct message displayed");
	  test.log(Status.INFO, "correct message display");

	  
	  
	 
  }
 
  @Test(priority=3)
  public void blankusername() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();
	  sl.login("", "secret_sauce");
	  Thread.sleep(3000);
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  System.out.println("access denied");
	  test.log(Status.INFO, "access denied");

	  Assert.assertTrue(sl.error().equals("Epic sadface: Username is required"),"Wrong message displayed");
	  System.out.println("correct message displayed");
	  test.log(Status.INFO, "correct message displayed");

	  
  }
  
  @Test(priority=4)
  public void blankpassword() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();
	  sl.login("standard_user", "");
	  Thread.sleep(3000);
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  System.out.println("access denied");
	  test.log(Status.INFO, "access denied");

	  
	  Assert.assertTrue(sl.error().equals("Epic sadface: Password is required"),"Wrong message displayed");
	  System.out.println("correct message displayed");
	  test.log(Status.INFO, "correct message displayed");

	  
  }
  
  @Test(priority=5)    //bug
  
  public void bothblank() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();
	  sl.login("", "");
	  Thread.sleep(3000);
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  System.out.println("access denied");
	  test.log(Status.FAIL, "access denied");

	  Assert.assertTrue(sl.error().equals("Epic sadface:username Password is required"),"Wrong message displayed");
	  System.out.println("correct message displayed");
	  test.log(Status.FAIL, "correct message displayed");

	  
	  
  }
  
  @Test(priority=6)    
  public void bothinvalid() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();
	  sl.login("afarwRwtwrw", "qreQRWETWtWT");
	  Thread.sleep(3000);
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  System.out.println("access denied");
	  test.log(Status.PASS, "access denied");

	  Assert.assertTrue(sl.error().equals("Epic sadface: Username and password do not match any user in this service"),"Wrong message displayed");
	  System.out.println("correct message displayed");
	  test.log(Status.PASS, "correct message displayed");

	  
  }
  
	  
  @Test(priority=7)    
  public void invalidpassword() throws Exception {
	  ExtentTest test = ExtentTestNGITestListener.testThread.get();
	  sl.login("standard_user", "secret_sauceawfafasge");
	  Thread.sleep(3000);
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
	  System.out.println("access denied");
	  test.log(Status.PASS, "access denied");

	  
	  Assert.assertTrue(sl.error().equals("Epic sadface: Username and password do not match any user in this service"),"Wrong message displayed");
	  System.out.println("correct message displayed");
	  test.log(Status.PASS, "correct message displayed");

	  
  }
  


	  
	  

 

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
