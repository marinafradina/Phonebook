package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if(!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User().setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();

        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new Contact()
                .setName("Klara")
                .setLastName("Adam")
                .setPhone("0987654321")
                .setEmail("adam@gm.com")
                .setAdress("Bonn")
                .setDescription("goalkeeper"));
        app.getContact().clickOnSaveButton();
    }
    @Test
    public void deletaContactPositiveTest() {
        int sizeBefore = app.getContact().sizeOFContacts();
        app.getContact().removeContact();
        app.getContact().pause(500);

        int sizeAfter = app.getContact().sizeOFContacts();
        Assert.assertEquals(sizeAfter, sizeBefore-1);
    }

}
