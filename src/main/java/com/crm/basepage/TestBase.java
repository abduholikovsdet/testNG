package com.crm.basepage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.crm.util.ExtentReportGenerator;
import com.crm.util.TestUtil;
import com.crm.util.WebEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/main/java/com/crm/config/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialization(){
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("edge")) {
            // Create EdgeOptions for headless mode
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
        }

//        // Wrap the WebDriver instance with EventFiringWebDriver and register the listener
//        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
//        WebDriverEventListener eventListener = new WebEventListener() {
//
//        };
//        eventDriver.register(eventListener);
//        driver = eventDriver;


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.DEFAULT_IMPLICIT_WAIT));
        driver.get(prop.getProperty("url"));
    }

    //reusable methods:

    /**
     * Author: Muzaffar A.
     * @param element
     * @return
     */
    public WebElement waitForVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((TestUtil.DEFAULT_EXPLICIT_WAIT)));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Author: Muzaffar A.
     * @param element
     * @return
     */
    public WebElement waitForClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds((TestUtil.DEFAULT_IMPLICIT_WAIT)));
        wait.ignoring(ElementNotInteractableException.class);
        wait.ignoring(WebDriverException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Author: Muzaffar A.
     * @param element
     */
    public void click(WebElement element){
        waitForClickable(element).click();
    }

    /**
     * Author: Muzaffar A.
     * @param element
     * @param text
     */
    public void inputText(WebElement element, String text){
        waitForVisible(element).sendKeys(text);
    }

    public void hoverOverElement(WebElement element){
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }








}
