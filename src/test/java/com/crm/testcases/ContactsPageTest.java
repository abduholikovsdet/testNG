package com.crm.testcases;

import com.crm.basepage.TestBase;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    public ContactsPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getTestData(){
        Object data[][] = TestUtil.excelReader("src/main/java/com/crm/testdata/testDataFile.xlsx");
        return data;
    }
    @Test(priority = 1)
    public void validateCreateNewContact(){
        contactsPage = homePage.openContactsPage();
        Assert.assertTrue(contactsPage.verifyContactsLabel());
    }
    @Test(priority = 2, dataProvider = "getTestData")
    public void createNewAccountTest(String fname, String mname, String lname, String compname, String email){
        contactsPage = homePage.openContactsPage();
        Assert.assertTrue(contactsPage.createNewContact(fname, mname, lname, compname, email));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
