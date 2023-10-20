package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import retry.RetryAnalyzer;

import java.time.Duration;

public class LoginTests extends BasicTest {

    private WebDriverWait wait;


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void visitsTheLoginPage() {
        navPage.getLanguageButton().click();
        navPage.getEnFromLanguage().click();
        navPage.getLoginLink().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "[ERROR] Url does not contain /login.");
    }


    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void checksInputTypes() {
        navPage.getLoginLink().click();
        Assert.assertEquals(loginPage.getEmail().getAttribute("type"),
                "email",
                "[ERROR] The email input type is not email.");

        Assert.assertEquals(loginPage.getPassword().getAttribute("type"),
                "password",
                "[EROOR] Password input type is not password.");
    }


    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void displaysErrorsWhenUserDoesNotExist() {
        String email = "non-existing-user@gmal.com";
        String password = "password123";

        navPage.getLoginLink().click();
        loginPage.getEmail().sendKeys(email);
        loginPage.getPassword().sendKeys(password);
        loginPage.getLoginButton().click();
        messagePopUpPage.waitUntilErrorWindowShowUp();
        Assert.assertTrue(messagePopUpPage.getErrorWindow().getText().contains("User does not exists"),
                "[ERROR] Pop-up message 'User does not exist' does not show.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "[ERROR] Url does not contain /login.");
    }


    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void displaysErrorsWhenPasswordIsWrong() {
        String email = "admin@admin.com";
        String password = "password123";

        navPage.getLoginLink().click();
        loginPage.getEmail().sendKeys(email);
        loginPage.getPassword().sendKeys(password);
        loginPage.getLoginButton().click();

        messagePopUpPage.waitUntilErrorWindowShowUp();
        Assert.assertTrue(messagePopUpPage.getErrorWindow().getText().contains("Wrong password"),
                "[ERROR] Pop-up message 'User does not exist' does not show.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "[ERROR] Url does not contain /login.");
    }


    @Test(priority = 5, retryAnalyzer = RetryAnalyzer.class)
    public void login() {
        String email = "admin@admin.com";
        String password = "12345";

        navPage.getLoginLink().click();
        loginPage.getEmail().sendKeys(email);
        loginPage.getPassword().sendKeys(password);
        loginPage.getLoginButton().click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/home"));
    }


    @Test(priority = 6, retryAnalyzer = RetryAnalyzer.class)
    public void logout() {
        Assert.assertTrue(navPage.getLogOutButton().isDisplayed(),
                "[ERROR] The Logout Button does not exist.");
        navPage.getLogOutButton().click();
    }
}