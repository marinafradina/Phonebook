package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AddContactTest extends TestBase{
    @BeforeMethod
    public void ensurePrecondition() {
        if(!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail("manyta123@gmx.de").setPassword("Manyta123@"));
        app.getUser().clickOnLoginButton();
    }
    @Test
    public void addContactPositiveTest() {
        //click on Add link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillAddContactForm(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.CONTACT_EMAIL)
                .setAdress(ContactData.ADRESS)
                .setDescription(ContactData.DESC));
        //click on Save button
        app.getContact().clickOnSaveButton();
        //assert Contact is added by text
        Assert.assertTrue(app.getContact().isContactCreated("Klara"));
    }

    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();

    }

    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String lastname, String phone,
                                                       String email, String address, String description) {
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new Contact()
                .setName(name)
                .setLastName(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAdress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(name));
    }



        @Test(dataProvider = "addNewContactFromCsvFile", dataProviderClass = DataProviders.class)
        public void addContactPositiveTestFromDataProviderWithCsvFile(Contact contact) {

            app.getContact().clickOnAddLink();
            app.getContact().fillAddContactForm(contact);
            app.getContact().clickOnSaveButton();
            Assert.assertTrue(app.getContact().isContactCreated(contact.getName()));
    }
}
