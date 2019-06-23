import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightBookingTest {

    WebDriver driver;


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

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
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
        waitFor(2000);
        driver.findElement(By.id("FromTag")).sendKeys(Keys.TAB);
         
        //wait for the auto complete options to appear for the origin

        
        
        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");
        waitFor(2000);
        driver.findElement(By.id("ToTag")).sendKeys(Keys.TAB);

        //wait for the auto complete options to appear for the destination

        waitFor(1000);
         
        
      
        driver.findElement(By.xpath("//*[@id='DepartDate']")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[4]/a")).click();      

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        
        String x=driver.getCurrentUrl();
                
        AssertJUnit.assertTrue(x.startsWith("https://www.cleartrip.com/flights/results"));
        
        //close the browser
        driver.quit();

    }


    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
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
