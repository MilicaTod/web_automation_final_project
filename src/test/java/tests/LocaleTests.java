package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import retry.RetryAnalyzer;

public class LocaleTests extends BasicTest {


    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void setLocaleToES() {
        navPage.getLanguageButton().click();
        navPage.getEsFromLanguage().click();
        Assert.assertTrue(navPage.getHeaderOfPage().getText().contains("Página de aterrizaje"));
    }


    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void setLocaleToEN() {
        navPage.getLanguageButton().click();
        navPage.getEnFromLanguage().click();
        Assert.assertTrue(navPage.getHeaderOfPage().getText().contains("Landing"));
    }


    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void setLocaleToCN() {
        navPage.getLanguageButton().click();
        navPage.getCnFromLanguage().click();
        Assert.assertTrue(navPage.getHeaderOfPage().getText().contains("首页"));
    }


    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void setLocaleToFR() {
        navPage.getLanguageButton().click();
        navPage.getFrFromLanguage().click();
        Assert.assertTrue(navPage.getHeaderOfPage().getText().contains("Page d'atterrissage"));
    }
}
