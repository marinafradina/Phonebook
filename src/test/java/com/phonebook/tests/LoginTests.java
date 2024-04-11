package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void ensurePrecobdition() {
        if(!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
    }

    @Test
    public void loginPositiveTest() {
        //click on Login Link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        //click on login button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }
    @Test
    public void loginNegativeTestWithoutEmail() {
        //click on Login Link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegisterForm(new User().setPassword(UserData.PASSWORD));
        //click on login button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is present
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}
