package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import retry.RetryAnalyzer;


public class AdminCitiesTests extends BasicTest {


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void visitsTheAdminCitiesPageAndListCities() {
        String email = "admin@admin.com";
        String password = "12345";

        navPage.getLoginLink().click();
        loginPage.getEmail().sendKeys(email);
        loginPage.getPassword().sendKeys(password);
        loginPage.getLoginButton().click();
        navPage.getAdminButton().click();
        navPage.getCitiesFromAdmin().click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/admin/cities"),
                "[ERROR] The URL doesn't contain /admin/cities.");
    }


    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void checksInputTypesForCreateEditNewCity() {
        navPage.getAdminButton().click();
        navPage.getCitiesFromAdmin().click();
        citiesPage.getNewItemButton().click();
        citiesPage.waitUntilNewItemWindowShowUp();
        Assert.assertEquals(citiesPage.getNewItemNameInput().getAttribute("type"),
                "text",
                "[ERROR] The new item name input type is not text.");
    }


    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void createNewCity() {
        String city = "Milica Todorovic's city";

        navPage.getAdminButton().click();
        navPage.getCitiesFromAdmin().click();
        citiesPage.getNewItemButton().click();
        citiesPage.waitUntilNewItemWindowShowUp();
        citiesPage.getNewItemNameInput().sendKeys(city);
        citiesPage.getSaveButtonFromEditAndNewItemWindows().click();
        citiesPage.waitUntilSuccessSaveCityPopup();
        Assert.assertTrue(citiesPage.getSuccessSaveCityPopUp().getText().contains("Saved successfully"),
                "[ERROR] The popup window doesn't contain 'Saved successfully'.");
    }


    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void editCity() {
        String oldCity = "Milica Todorovic's city";
        String newCity = "Milica Todorovic's city Edited";
        navPage.getAdminButton().click();
        navPage.getCitiesFromAdmin().click();
        citiesPage.getSearchInput().sendKeys(oldCity);
        citiesPage.waitUntilShowSelectedNumberOfRows(1);
        citiesPage.getEditButton(1).click();
        citiesPage.getNewItemNameInput().click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL)
                .sendKeys(Keys.chord("a"))
                .keyUp(Keys.CONTROL)
                .perform();
        citiesPage.getEditNewCityInput().sendKeys(newCity);
        citiesPage.getSaveButtonFromEditAndNewItemWindows().click();
        citiesPage.waitUntilSuccessSaveCityPopup();
        Assert.assertTrue(citiesPage.getSuccessSaveCityPopUp().getText().contains("Saved successfully"),
                "[ERROR] The popup window doesn't contain 'Saved successfully'");
    }


    @Test(priority = 5, retryAnalyzer = RetryAnalyzer.class)
    public void searchCity() {
        String city = "Milica Todorovic's city Edited";

        navPage.getAdminButton().click();
        navPage.getCitiesFromAdmin().click();
        citiesPage.getSearchInput().sendKeys(city);
        citiesPage.waitUntilShowSelectedNumberOfRows(1);
        Assert.assertEquals(citiesPage.getSelectedCell(1, 2).getText(),
                city,
                "[ERROR] The name of the city doesn't match the selected name.");
    }


    @Test(priority = 6, retryAnalyzer = RetryAnalyzer.class)
    public void deleteCity() {
        String city = "Milica Todorovic's city Edited";

        navPage.getAdminButton().click();
        navPage.getCitiesFromAdmin().click();
        citiesPage.getSearchInput().sendKeys(city);
        citiesPage.waitUntilShowSelectedNumberOfRows(1);
        Assert.assertEquals(citiesPage.getSelectedCell(1, 2).getText(),
                city,
                "[ERROR] The name of the city doesn't match the selected name.");
        citiesPage.getDeleteButton(1).click();
        citiesPage.waitUntilDeleteWindowShow();
        citiesPage.getDeleteButtonFromDeleteWindow().click();
        citiesPage.waitUntilDeletedCityPopup();
    }
}