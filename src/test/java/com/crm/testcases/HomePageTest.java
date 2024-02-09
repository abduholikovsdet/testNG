package com.crm.testcases;

import com.crm.basepage.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{

    LoginPage loginPage;
    HomePage homePage;

    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void verifyHomePageTitle(){
        Assert.assertEquals(homePage.verifyHomePageTitle(), "Cogmento CRM", "Home page title not matched");
    }

    @Test(priority = 2)
    public void verifyUserName(){
        Assert.assertTrue( homePage.validateUserNameLabel("Muzaffar Abduholikov"), "User's full name not matched");
    }





    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
