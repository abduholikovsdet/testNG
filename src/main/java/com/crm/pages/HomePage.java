package com.crm.pages;

import com.crm.basepage.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    @FindBy(xpath = "//span[@class='user-display']")
    WebElement userNameLabel;
    @FindBy(css = "div#main-nav")
    WebElement mainNavigationBar;
    @FindBy(css = "a[href='/contacts']")
    WebElement contactsIcon;



    HomePage(){
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();
    }

    public boolean validateUserNameLabel(String userFullName){
        return waitForVisible(userNameLabel).getText().equals(userFullName);
    }

    public ContactsPage openContactsPage(){
//        hoverOverElement(mainNavigationBar);
        click(contactsIcon);
        return new ContactsPage();
    }



}
