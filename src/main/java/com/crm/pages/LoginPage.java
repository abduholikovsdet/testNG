package com.crm.pages;

import com.crm.basepage.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    @FindBy(xpath = "//span[text()='Log In']")
    WebElement loginBtn;

    @FindBy(name = "email")
    WebElement usrname;
    @FindBy(name = "password")
    WebElement pwd;
    @FindBy(xpath = "//*[@class = 'ui fluid large blue submit button']")
    WebElement submitBtn;

    @FindBy(xpath = "//a[@title='free crm home']")
    WebElement crmLogo;

    //Initializing the Page objects with Constructor
    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public String validateLoginPageTitle(){

        return driver.getTitle();
    }

    public boolean validateCRMLogo(){
        return waitForVisible(crmLogo).isDisplayed();
    }

    public HomePage login(String usrNme, String pswd){
        click(loginBtn);
        inputText(usrname, usrNme);
        inputText(pwd, pswd);
        click(submitBtn);

        return new HomePage();
    }


}
