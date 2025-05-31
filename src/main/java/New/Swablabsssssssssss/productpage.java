package New.Swablabsssssssssss;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class productpage {


	WebDriver driver;
	WebDriverWait wait;




	public productpage(WebDriver driver) {

		this.driver = driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	public String proname() {
 
         WebElement name=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_details_name']")));
          String pname=name.getText();
          
          return pname;
	}

	public String proprice() {
		 
        WebElement price=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_details_price']")));
         String pprice=price.getText();
         
         return pprice;
	}

	public void aaacart()
	{
		WebElement cart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ADD TO CART']")));
		cart.click();
	}


	public void premove()
	{
		WebElement remo=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='REMOVE']")));
		remo.click();
	}
    


	public void pback()
	{
		WebElement back=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='inventory_details_back_button']")));
		back.click();
	}
    

}
