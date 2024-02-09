package com.crm.testcases;

import com.aventstack.extentreports.Status;
import com.crm.basepage.TestBase;
import com.crm.extentReportListener.HtmlReporterListener;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    public LoginPageTest(){
        super();
    }
    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();

    }
    @Test(priority = 1)
    public void loginPageTestTest(){

        String title = loginPage.validateLoginPageTitle();
        Assert.assertEquals(title, "#1 Free CRM Power Up your Entire Business Free Forever");

    }

    @Test(priority = 2)
    public void crmLogoImageTest(){

        Assert.assertTrue(loginPage.validateCRMLogo());
    }
    @Test(priority = 3)
    public void loginTest(){

        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }







}
