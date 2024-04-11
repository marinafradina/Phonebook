package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{

    @BeforeMethod
    public void ensurePrecobdition() {
        if(!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSingOutButton();
        }
    }

    @Test(enabled = false)
    public void createNewAcoountPositiveTest() {
//        Random random = new Random();
//        int i = random.nextInt(1000) + 1000;
        //click on Login Link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegisterForm(new User().setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //asert Sign Out button is present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void createNewAcoountWithExistedEmailNegativeTest() {
        //click on Login Link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegisterForm(new User().setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //asert Alert is present
        Assert.assertTrue(app.getUser().isAlertPresent());

    }

}
