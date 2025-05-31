package New.Swablabsssssssssss;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class swaghometest {

	WebDriver driver;
	WebDriver wait;
	swaglogin sl;
	swaghome sh;
	cartpage cp;
	productpage pp;

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {


		driver=new ChromeDriver();
		sl=new swaglogin(driver);
		sl.get("https://www.saucedemo.com/v1/index.html");
		sl.login("standard_user", "secret_sauce");
		sh=new swaghome(driver);
		cp=new cartpage(driver);
		pp=new productpage(driver);
		
	}


	@Test(priority=1)
	public void menuclick() throws InterruptedException
	{

		sh.menu();
		Assert.assertTrue(sh.navPanel(), "Menu button is not working");
		System.out.println("Menu button is working");
		Thread.sleep(3000);
		SoftAssert sa=new SoftAssert();
		sh.Allitems();
		Thread.sleep(3000);
		sa.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "All Items button is not working");
		sh.menu();
		Thread.sleep(3000);
		sh.about();
		Thread.sleep(3000);
		//sa.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"),"About button is not working");
		sa.assertTrue(driver.getPageSource().contains("Build apps users love with AI-driven insights"), "About button is not working");
		Thread.sleep(3000);
		driver.navigate().to("https://www.saucedemo.com/v1/inventory.html");
		sa.assertAll();
	}


	@Test(priority=2)
	public void dropdown() throws InterruptedException 
	{
		SoftAssert sa=new SoftAssert();
		List<String> beforesortz=sh.products();
		System.out.println(beforesortz);
		Thread.sleep(3000);
		sh.za();
		List<String> aftersortz=sh.products();
		System.out.println(aftersortz);
		sa.assertNotEquals(aftersortz,beforesortz,"Sort not working");
		System.out.println("sort is working");
		sa.assertAll();
		
		List<String>beforesorta=sh.products();
		System.out.println(beforesorta);
		sh.az();
		List<String>aftersorta=sh.products();
		System.out.println(aftersorta);
		sa.assertNotEquals(aftersorta,beforesorta,"sort not working");
		System.out.println("sort is working");
		sa.assertAll();
		
        
		List<Double>beforesortt=sh.productprice();
		System.out.println(beforesortt);
		sh.lowtohigh();
		List<Double>aftersortt=sh.productprice();
		System.out.println(aftersortt);
		sa.assertNotEquals(aftersortt,beforesortt,"sort not working");
		System.out.println("sort is working");
		sa.assertAll();
		
		List<Double>beforesorttt=sh.productprice();
		System.out.println(beforesorttt);
		sh.hightolow();
		List<Double>aftersorttt=sh.productprice();
		System.out.println(aftersorttt);
		sa.assertNotEquals(aftersorttt,beforesorttt,"sort not working");
		System.out.println("sort is working");
		sa.assertAll();
		
		}

	
	@Test(priority=3)
	public void Addtocart() throws InterruptedException 
	{Thread.sleep(5000);
	
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
		
		cp.removecart();
	//	Assert.assertEquals(cp.products(),null,"remove button is not working");Assert.assertTrue(cp.products().isEmpty(), "remove button is not working");
		List<String>productsincpar=cp.products();
		Assert.assertTrue(productsincpar.isEmpty(), "remove button is not working");

		System.out.println("remove button is working");
		
		}
	
	


	@Test(priority=4)
	public void homer() throws InterruptedException 
	{
	       

       sh.cart();
     

       sh.removecart();
       
       Assert.assertTrue(sh.checkRemove(), "Products are not removed from cart from product page");
		System.out.println("Products are removed from cart from product page");
		
		
	}
           
	

	@Test(priority=5)
	public void productname() throws InterruptedException 
	{
	       
       Thread.sleep(4000);
       String product="Sauce Labs Bike Light";
       String hpPrice=sh.clickproductname(product);
       Assert.assertTrue(pp.proname().equals(product),"product name not matches");
       System.out.println("The product name matches");
       
       
       
       
       String dpprice=pp.proprice();
       Assert.assertEquals(dpprice,hpPrice, "price not matches");
       System.out.println("price matches");
       
	
       pp.aaacart();
       sh.ncart();
	Assert.assertTrue(cp.products().contains(product),"product is not in the cart");
	System.out.println("product is in the cart");
	
	driver.navigate().back();
	pp.premove();
	sh.ncart();
	Assert.assertFalse(cp.products().contains(product),"product is still in the cart");
	System.out.println("product is not in the cart");
	driver.navigate().back();
	pp.pback();
	Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"not navigated to the homepage");
	System.out.println("navigated to the homepage");
	sh.ncart();
	cp.cshop();
	Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"not navigated to homepage");
	System.out.println("Navigated to homeage");
	sh.ncart();
	cp.cout();
	
	}
	
	


	@Test(priority=6)
	public void checkout() throws InterruptedException 
	{
		Thread.sleep(4000);
	       sh.singlp();
	       sh.ncart();
	       cp.cout();
	       Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/cart.html"),"not navigated to cart page");
	       System.out.println("navigated to cart page");
	       
      
	       
	
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	@AfterMethod
	public void afterMethod() {
		
		driver.close();
	}

}
