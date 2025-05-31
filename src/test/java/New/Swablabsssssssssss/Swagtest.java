package New.Swablabsssssssssss;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

@Listeners(ExtentTestNGITestListener.class)
public class Swagtest {
	WebDriver driver;
	swaglogin sl;
	swaghome sh;
	cartpage cp;
	productpage pp;

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() throws InterruptedException {
		driver=new ChromeDriver();
		sl=new swaglogin(driver);
		sl.get("https://www.saucedemo.com/v1/index.html");
		sh=new swaghome(driver);
		cp=new cartpage(driver);
		pp=new productpage(driver);
	}


	@Test(priority=1)
	public void validlogin() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
		sl.login("standard_user", "secret_sauce");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
		System.out.println("user logged in");
		test.log(Status.INFO,"Checking loging sucess for valid loging"); //to know status pass fail
		sh.menu();
		sh.logout();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"),"not displayed");
		System.out.println("correct page displayed");
		test.log(Status.INFO,"Checking logout  for valid login");
		
	}

	
	@Test(priority=2)
	public void invalidusername() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();
		sl.login("standard_user1", "secret_sauce");
		Thread.sleep(3000);

		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));

		System.out.println("access denied");
		test.log(Status.INFO,"checking login failed for invalid username");

		Assert.assertTrue(sl.error().equals("Epic sadface: Username and password do not match any user in this service"),"Wrong message displayed");
		System.out.println("correct message displayed");
		test.log(Status.INFO, "Checking error message for invalid username");

	}

	
	@Test(priority=3)
	public void blankusername() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();
		sl.login("", "secret_sauce");
		Thread.sleep(3000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user cant loggedin");
		System.out.println("user can loggedin");
		test.log(Status.INFO,"Checking login with blank username");
		Assert.assertTrue(sl.error().equals("Epic sadface: Username is required"),"Wrong message displayed");
		System.out.println("correct message displayed");
		test.log(Status.INFO, "checking error message for blank username");

	}

	
	@Test(priority=4)
	public void blankpassword() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();
		sl.login("standard_user", "");
		Thread.sleep(3000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
		System.out.println("Checking login with blank password");
		test.log(Status.INFO,"access denied");//Result log into the report
		Assert.assertTrue(sl.error().equals("Epic sadface: Password is required"),"Wrong message displayed");
		System.out.println("correct message displayed");
		test.log(Status.INFO, "Checking error message for blankpassword");
	}
	

	@Test(priority=5)    //bug

	public void bothblank() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();
		sl.login("", "");
		Thread.sleep(3000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user cant loggedin");
		System.out.println("User is denied access");
		test.log(Status.FAIL,"Checking login failed for bothblank");
		Assert.assertTrue(sl.error().equals("Epic sadface: Password is required"),"Wrong message displayed");
		System.out.println("correct message displayed");
		test.log(Status.FAIL, "Checking error message for both leaving blank");
	}
	

	@Test(priority=6)    
	public void bothinvalid() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();
		sl.login("afarwRwtwrw", "qreQRWETWtWT");
		Thread.sleep(3000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
		System.out.println("access denied");
		test.log(Status.PASS, "Checking login failed with both invalid details");

		Assert.assertTrue(sl.error().equals("Epic sadface: Username and password do not match any user in this service"),"Wrong message displayed");
		System.out.println("correct message displayed");
		test.log(Status.PASS, "checking error message for both invalid details");
		
	}

	

	@Test(priority=7)    
	public void invalidpassword() throws Exception {
		ExtentTest test = ExtentTestNGITestListener.testThread.get();
		sl.login("standard_user", "secret_sauceawfafasge");
		Thread.sleep(3000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));
		System.out.println("access denied");
		test.log(Status.PASS, "checking login failed with invalid password");

		Assert.assertTrue(sl.error().equals("Epic sadface: Username and password do not match any user in this service"),"Wrong message displayed");
		System.out.println("correct message displayed");
		test.log(Status.PASS, "Checking error message for invalid password login");


	}


	@Test(priority=8)
	public void menuclick() throws InterruptedException
	{
		ExtentTest test = ExtentTestNGITestListener.testThread.get();  //get textname //onstart
        sl.login("standard_user", "secret_sauce");
		sh.menu();
		Assert.assertTrue(sh.navPanel(), "Menu button is not working");
		System.out.println("Menu button is working");
		test.log(Status.INFO, "Checking menu button");

		Thread.sleep(3000);
		SoftAssert sa=new SoftAssert();
		sh.Allitems();
		Thread.sleep(3000);
		sa.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "All Items button is not working");
		test.log(Status.INFO, "Checking all items button");

		sh.menu();
		Thread.sleep(3000);
		sh.about();
		Thread.sleep(3000);
		//sa.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"),"About button is not working");
		sa.assertTrue(driver.getPageSource().contains("Build apps users love with AI-driven insights"), "About button is not working");
		test.log(Status.INFO, "Checking about button");

		Thread.sleep(3000);
		driver.navigate().to("https://www.saucedemo.com/v1/inventory.html");
		sa.assertAll();
	}


	@Test(priority=9)
	public void dropdown() throws InterruptedException 
	{
		ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
		sl.login("standard_user", "secret_sauce");
		SoftAssert sa=new SoftAssert();

		List<String> beforesortz=sh.products();
		System.out.println(beforesortz);
		Thread.sleep(3000);
		sh.za();
		List<String> aftersortz=sh.products();
		System.out.println(aftersortz);
		sa.assertNotEquals(aftersortz,beforesortz,"Sort not working");
		System.out.println("sort is working");
		test.log(Status.INFO, "Checking name desending sort");

		

		List<String>beforesorta=sh.products();
		System.out.println(beforesorta);
		sh.az();
		List<String>aftersorta=sh.products();
		System.out.println(aftersorta);
		sa.assertNotEquals(aftersorta,beforesorta,"sort not working");
		
		System.out.println("sort is working");
		test.log(Status.INFO, "Checking name ascending sort");



		List<Double>beforesortt=sh.productprice();
		System.out.println(beforesortt);
		sh.lowtohigh();
		List<Double>aftersortt=sh.productprice();
		System.out.println(aftersortt);
		sa.assertNotEquals(aftersortt,beforesortt,"sort not working");
		System.out.println("sort is working");
		test.log(Status.INFO, "Checking price low to high sort");

	

		List<Double>beforesorttt=sh.productprice();
		System.out.println(beforesorttt);
		sh.hightolow();
		List<Double>aftersorttt=sh.productprice();
		System.out.println(aftersorttt);
		sa.assertNotEquals(aftersorttt,beforesorttt,"sort not working");
		System.out.println("sort is working");
		test.log(Status.INFO, "Checking price high to low sort");

		sa.assertAll();

	}


	@Test(priority=10)
	public void Addtocart() throws InterruptedException 
	{      
		ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
        sl.login("standard_user", "secret_sauce");

     	Thread.sleep(5000);

		List<String>productsinpp=sh.products();
		System.out.println(productsinpp);
		sh.cart();
		Thread.sleep(2000);
		sh.ncart();
		Thread.sleep(2000);
		List<String>productsincp=cp.products();
		System.out.println(productsincp);
		Assert.assertEquals(productsincp, productsinpp,"All products are not found in cart");
		System.out.println("All products found in cart");
		test.log(Status.INFO, "Checking wheather products are added to the cart");


		cp.removecart();
		//	Assert.assertEquals(cp.products(),null,"remove button is not working");Assert.assertTrue(cp.products().isEmpty(), "remove button is not working");
		List<String>productsincpar=cp.products();
		Assert.assertTrue(productsincpar.isEmpty(), "remove button is not working");

		System.out.println("remove button is working");
		test.log(Status.INFO, "Checking wheather products are remove to the cart");


	}




	@Test(priority=11)
	public void homer() throws InterruptedException 
	{
		ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
        sl.login("standard_user", "secret_sauce");

     	sh.cart();

     	sh.removecart();

		Assert.assertTrue(sh.checkRemove(), "Products are not removed from cart from product page");
		System.out.println("Products are removed from cart from product page");


	}



	@Test(priority=12)
	public void productname() throws InterruptedException 
	{
		ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
		sl.login("standard_user", "secret_sauce");


		Thread.sleep(4000);
		String product="Sauce Labs Bike Light";
		String hpPrice=sh.clickproductname(product);
		Assert.assertTrue(pp.proname().equals(product),"product name not matches");
		System.out.println("The product name matches");
		test.log(Status.INFO, "Cheking wheather the user is navigated to the crct pdp when clicked on product name");





		String dpprice=pp.proprice();
		Assert.assertEquals(dpprice,hpPrice, "price not matches");
		System.out.println("price matches");
		test.log(Status.INFO, "Checking wheather the price from hp and pdp are same");



		pp.aaacart();
		sh.ncart();
		Assert.assertTrue(cp.products().contains(product),"product is not in the cart");
		System.out.println("product is in the cart");
		test.log(Status.INFO, "Checking wheather products added to the cart from pdp");


		driver.navigate().back();
		pp.premove();
		sh.ncart();
		Assert.assertFalse(cp.products().contains(product),"product is still in the cart");
		System.out.println("product is not in the cart");
		test.log(Status.INFO, "Checking wheather products are remove from the cart");

		driver.navigate().back();
		pp.pback();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"not navigated to the homepage");
		System.out.println("navigated to the homepage");
		test.log(Status.INFO, "Checking the back button from pdp");

		sh.ncart();
		cp.cshop();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"not navigated to homepage");
		System.out.println("Navigated to homeage");
		test.log(Status.INFO, "Checking the continue shopping button from the cart");

		sh.ncart();
		cp.cout();

	}




	@Test(priority=13)
	public void checkout() throws InterruptedException 
	{  
		ExtentTest test = ExtentTestNGITestListener.testThread.get();//get textname //onstart
		sl.login("standard_user", "secret_sauce");

		Thread.sleep(4000);
		sh.singlp();
		sh.ncart();
		cp.cout();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/checkout-step-one.html"),"not navigated to checkout page");
		System.out.println("navigated to checkout pape");
		test.log(Status.INFO, "Checking the checkout button");





	}


	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
		if(driver!=null)
		{
			driver.quit();
		}
	}

}
