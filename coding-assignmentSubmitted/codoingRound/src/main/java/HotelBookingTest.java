import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.sun.javafx.PlatformUtil;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelBookingTest {

	
	WebDriver driver;    

  
    @Test 
     
    public  void shouldBeAbleToSearchForHotels() throws InterruptedException {    	
    	setDriverPath();        	
        Map<String, Object> prefs = new HashMap<String, Object>();
        //add key and value to map as follow to switch off browser notification
        //Pass the argument 1 to allow and 2 to block
        prefs.put("profile.default_content_setting_values.notifications", 2);
        //Create an instance of ChromeOptions 
        ChromeOptions options = new ChromeOptions();
        // set ExperimentalOption - prefs 
        options.setExperimentalOption("prefs", prefs);        
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();        
        driver.get("https://www.cleartrip.com/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/section[2]/div/aside[1]/nav/ul[1]/li[2]/a[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#Tags")).sendKeys("Indiranagar, Bangalore");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#Tags")).sendKeys(Keys.TAB);
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(1000);
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("travellersOnhome"))).selectByVisibleText("1 room, 2 adults");
        Thread.sleep(2000);
        driver.findElement(By.id("SearchHotelsButton")).click();       
        Thread.sleep(5000);        	
        String y=driver.getCurrentUrl();        
        Assert.assertTrue(y.startsWith("https://www.cleartrip.com/hotels/results"));
        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
