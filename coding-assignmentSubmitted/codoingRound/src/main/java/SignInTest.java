import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.sun.javafx.PlatformUtil;
import com.sun.javafx.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver ;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();//Create a map to store  preferences 
        Map<String, Object> prefs = new HashMap<String, Object>();

      //add key and value to map as follow to switch off browser notification
      //Pass the argument 1 to allow and 2 to block
      prefs.put("profile.default_content_setting_values.notifications", 2);

      //Create an instance of ChromeOptions 
      ChromeOptions options = new ChromeOptions();

      // set ExperimentalOption - prefs 
      options.setExperimentalOption("prefs", prefs);

      //Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
      WebDriver driver = new ChromeDriver(options);
      driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
         WebDriverWait wait1=new WebDriverWait(driver, 20);
        WebElement waitforwebsite;
        waitforwebsite = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[1]/div/div[1]/a/span")));
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
        
        try {
            Robot robot1 = new Robot();

            robot1.keyPress(KeyEvent.VK_TAB);
            
            robot1.keyPress(KeyEvent.VK_TAB);
            
            robot1.keyPress(KeyEvent.VK_ENTER);
            waitFor(1000);
            
            robot1.keyPress(KeyEvent.VK_UP);
            
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        
        driver.switchTo().frame("modal_window");
        String errors1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/span")).getText();
        AssertJUnit.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver","C://Users//ARANI//workspace//codingRound-master//codingRound-master//chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
