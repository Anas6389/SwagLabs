package New.Swablabsssssssssss;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cartpage {
			
		WebDriver driver;
		WebDriverWait wait;
		
	
	public cartpage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
		
		
	public List<String> products() {
	    List<String> productNames = new ArrayList<>();
	    try {
	        // Use presence instead of visibility
	        List<WebElement> productElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'inventory_item_name')]")));
	        for (WebElement product : productElements) {
	            if (product.isDisplayed()) {
	                productNames.add(product.getText());
	            }
	        }

	    } catch (TimeoutException e) {
	        System.out.println("Timed out waiting for product elements. Possibly the page hasn't loaded correctly.");
	    }

	    if (productNames.isEmpty()) {
	        System.out.println("Cart is empty or product names are not visible.");
	    }

	    return productNames;
	}
	
	 
		public void removecart() {
			
			List<WebElement>removec=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='REMOVE']")));
			for(WebElement c:removec) {
				
				
				c.click();
				
			}	
			
		}



	
	
	
	
	
		public void cshop()
		{
			WebElement shop=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Continue Shopping']")));
			shop.click();
		}
	    
		
		public void cout()
		{
			WebElement check=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='CHECKOUT']")));
			check.click();
		}
	
	
	
	
	
}
