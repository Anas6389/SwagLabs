
package New.Swablabsssssssssss;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class swaglogin {
	
       WebDriver driver;
       WebDriverWait Wait;
       
       
       public swaglogin(WebDriver driver) {
    	   
    	   this.driver=driver;
        	this.Wait=new WebDriverWait(driver,Duration.ofSeconds(10));

       }
       
       public void get(String URL) {
    	   
    	   driver.get(URL);
    	   driver.manage().window().maximize();
    	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	   
    	   
       }
       
       public void login(String username,String password) throws InterruptedException {
    	   
    	   driver.findElement(By.id("user-name")).sendKeys(username);
    	   driver.findElement(By.id("password")).sendKeys(password);
    	   driver.findElement(By.id("login-button")).click();
    	   Thread.sleep(5000);
//    	   try{
//    		   Alert a=Wait.until(ExpectedConditions.alertIsPresent());
//    		   a=driver.switchTo().alert();
//    		   a.accept();
//    	   }
//    	   catch(Exception e){
//    		   System.out.println("No alert is present after login");
//    		 }
    	   
       }
	
       public String error() {
    	   WebElement emsg = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
    	   String ermsg=emsg.getText();
    	   return ermsg;
	

}
       
       
       
}
