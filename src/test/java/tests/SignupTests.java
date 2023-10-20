package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import retry.RetryAnalyzer;

public class SignupTests extends BasicTest {

    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void visitsTheSignupPage() {
        navPage.getSignUplink().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/signup"),
                "[ERROR] The URL doesn't contain a /signup.");
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void checksInputTypes() {
        navPage.getSignUplink().click();
        Assert.assertEquals(signupPage.getEmail().getAttribute("type"),
                "email",
                "[ERROR] The email input type is not email.");
        Assert.assertEquals(signupPage.getPassword().getAttribute("type"),
                "password",
                "[ERROR] Password input type is not password.");
        Assert.assertEquals(signupPage.getConfirmPassword().getAttribute("type"),
                "password",
                "[ERROR] Confirm password input type is not password");
    }


    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void displaysErrorsWhenUserAlreadyExists() {
        String name = "Another User";
        String email = "admin@admin.com";
        String password = "12345";
        String confPass = "12345";

        navPage.getSignUplink().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/signup"),
                "[ERROR] The URL doesn't contain a signup.");
        signupPage.getName().sendKeys(name);
        signupPage.getEmail().sendKeys(email);
        signupPage.getPassword().sendKeys(password);
        signupPage.getConfirmPassword().sendKeys(confPass);
        signupPage.getSignupButton().click();
        messagePopUpPage.waitUntilErrorWindowShowUp();
        Assert.assertTrue(messagePopUpPage.getErrorWindow().getText().contains("E-mail already exists"),
                "[ERROR] PopUp massage 'E-mail already exists");
        Assert.assertTrue(driver.getCurrentUrl().contains("/signup"),
                "[ERROR] The URL doesn't contain a signup.");
    }


    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void signup() {
        String name = "Milica Todorovic Jovanovic";
        String email = "milica.todorovic@itbootcamp.rs";
        String password = "12345";
        String confPass = "12345";

        navPage.getSignUplink().click();
        signupPage.getName().sendKeys(name);
        signupPage.getEmail().sendKeys(email);
        signupPage.getPassword().sendKeys(password);
        signupPage.getConfirmPassword().sendKeys(confPass);
        signupPage.getSignupButton().click();
        messagePopUpPage.waitUntilVerifyAccountWindowShowUp();
        messagePopUpPage.closeButtonFromPopupWindow().click();
        navPage.getLogOutButton().click();

    }
}