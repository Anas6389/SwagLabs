package New.Swablabsssssssssss;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class swaghome {

	WebDriver driver;
	WebDriverWait wait;




	public swaghome(WebDriver driver) {

		this.driver = driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	//public boolean menu() {
	//	
	//	WebElement menuI= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='bm-burger-button']")));
	//	menuI.click();
	//	
	//	if(driver.findElement(By.xpath("//div[@class='bm-menu']")).isDisplayed()) {
	//		
	//		return true;
	//		
	//	}
	//	return false;
	//	
	//}

	public void menu() {
		WebElement menuI= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='bm-burger-button']")));
		menuI.click();
	}
	
	public boolean navPanel()
	{
		WebElement panel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bm-menu']")));
		if(panel.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	
	


	public void Allitems() {
		WebElement abt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='inventory_sidebar_link']")));
		abt.click();
	}


	public void about() {
		WebElement abt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='about_sidebar_link']")));
		abt.click();

	}

	public void logout() {

		WebElement logout_button=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='logout_sidebar_link']")));
		logout_button.click();

	}
	


	public void appresetState() {
		WebElement ars=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='reset_sidebar_link']")));
		ars.click();

	}
	

	public String clickproductname(String productname) {
		List<WebElement>pl=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item']")));
		for(WebElement product:pl) {
			WebElement prodName=product.findElement(By.xpath(".//div[@class='inventory_item_name']"));
			wait.until(ExpectedConditions.visibilityOf(prodName));
			String prodText=prodName.getText();
			if(prodText.equalsIgnoreCase(productname)) {

				WebElement productprice=product.findElement(By.xpath(".//div[@class='inventory_item_price']"));
				wait.until(ExpectedConditions.visibilityOf(productprice));
				String productprices=productprice.getText();
				wait.until(ExpectedConditions.elementToBeClickable(prodName));
				prodName.click();
				return productprices;
			}
		}

		return null;



	}


	public List<String> products() {

		List <WebElement> prodlist=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_name']")));
		List <String> productsName=new ArrayList<>();
		for(WebElement product:prodlist)
		{
			
			String pname=product.getText();
			productsName.add(pname);

			//System.out.println(productsName);
		}
		return productsName;
	}


	public List<Double> productprice() {

		List <WebElement> prodlist=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_price']")));
		List<Double>prices=new ArrayList<>();
		for(WebElement product:prodlist) {
			String pprice=product.getText().replace("$","").trim();
			prices.add(Double.parseDouble(pprice));
		}
		return prices;

	}
	
	public void singlp() {
		WebElement sp=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ADD TO CART']")));
		sp.click();
		

	}


	public void za() {
		Select sel=new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		sel.selectByValue("za");

	}

	public void az() { 
		Select sel=new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		sel.selectByValue("az");
	}

	public void lowtohigh() {

		Select sel=new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		sel.selectByValue("lohi");

	}


	public void hightolow() {

		Select sel=new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		sel.selectByValue("hilo");

	}

	public void cart() {

		List<WebElement> cartt=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='ADD TO CART']")));
		for(WebElement ka:cartt) {

			
			ka.click();	}

	}

	public void ncart() {

		WebElement c=wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
		c.click();
	}



	public void removecart() {

		List<WebElement>removec=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='REMOVE']")));
		for(WebElement c:removec) {

			
			c.click();

		}


	}
	



	public Boolean checkRemove()
	{
		List<WebElement> cartt=driver.findElements(By.xpath("//div[@class='inventory_item']"));
		for(WebElement ka:cartt)
		{

			WebElement cclik=ka.findElement(By.xpath(".//button[@class='btn_primary btn_inventory']"));
			if(cclik.getText().equals("ADD TO CART"))
			{
				return true;
			}
		}
		return false;

	}


}