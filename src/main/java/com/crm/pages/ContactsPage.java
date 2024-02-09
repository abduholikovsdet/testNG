package com.crm.pages;

import com.crm.basepage.TestBase;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage extends TestBase {
    @FindBy(xpath = "//span[@class='selectable ' and text()='Contacts']")
    @CacheLookup //stores it in cache memory. Improves performance
    WebElement contactsLabel;
    @FindBy(xpath = "//button[text()='Create']")
    WebElement createBtn;
    @FindBy(xpath = "//span[text()='Create New Contact']")
    WebElement createNewContactLabel;
    @FindBy(name = "first_name")
    WebElement firstName;
    @FindBy(name = "middle_name")
    WebElement middleName;
    @FindBy(name = "last_name")
    WebElement lastName;
    @FindBy(xpath = "//div[@name='company']//input[@type='text']")
    WebElement company;
    @FindBy(xpath = "//input[@placeholder='Email address']")
    WebElement email;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveBtn;



    public ContactsPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean verifyContactsLabel(){
        return waitForVisible(contactsLabel).isDisplayed();
    }

    public boolean createNewContact(String fName, String mName, String lName, String companyName, String em){
        String fullName = fName+" " + lName;
        click(createBtn);
        waitForVisible(createNewContactLabel);
        inputText(firstName, fName);
        inputText(middleName, mName);
        inputText(lastName, lName);
        inputText(company, companyName);
        inputText(email, em);
        click(saveBtn);
        return waitForVisible(driver.findElement(By.xpath("//span[text()='"+fullName+"']"))).isDisplayed();

    }





}
