package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//Cities Page
//dugme New Item
//input za pretragu
//metodu koja ceka da se dijalog za editovanje i kreiranje pojavi
//metodu koja ceka da se dijalog za brisanje pojavi
//save dugme iz dijalog za editovanje i kreiranje
//delete dugme iz dijaloga za brisanje
//metodu koja ceka da se u tabeli pojavi odredjeni broj redova. Metoda kao parametar prima broj redova.
// Ako se metoda pozove sa parametrom 5, to znaci da metoda ceka da se u tabeli pojavi 5 redova.
//metodu koja vraca celiju iz odredjenog reda. Metoda kao parametre prima red i kolonu a vraca td iz tabele.
// Npr: ukoliko se metoda pozove row=2, cell=3 tada metoda vraca trecu celiju iz 2 reda.
//metodu koja vraca edit dugme trazenog reda. Metoda kao parametar prima red a vraca dugme.
// Npr ako se metoda pozove row=3, metoda vraca edit dugme iz treceg reda tabele
//metodu koja vraca delete dugme trazenog reda. Metoda kao parametar prima red a vraca dugme.
// Npr ako se metoda pozove row=3, metoda vraca delete dugme iz treceg reda tabele
public class CitiesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CitiesPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNewItemButton(){
        return driver.findElement(By.className("btnNewItem"));
    }

    public WebElement getNewItemNameInput(){
        return driver.findElement(By.id("name"));
    }

    public WebElement getSearchInput(){
        return driver.findElement(By.id("search"));
    }

    public void waitUntilEditWindowShowUp(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.className("headline")), "Edit Item"));
    }

    public void waitUntilNewItemWindowShowUp(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
    }


    public WebElement getSaveButtonFromEditAndNewItemWindows(){
        return driver.findElement(By.className("btnSave"));
    }

    public WebElement getSuccessSaveCityPopUp(){
        return driver.findElement(By.xpath("//*[contains(text(), ' Saved successfully ')]"));
    }

    public void waitUntilSuccessSaveCityPopup(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text()," +
                "' Saved successfully ')]")));
    }

    public WebElement getDeleteButtonFromDeleteWindow(){
        return driver.findElement(By.className("text--lighten3"));
    }

    public void waitUntilDeleteWindowShow(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("py-3")));
    }

    public void waitUntilDeletedCityPopup(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text()," +
                " 'Deleted successfully')]")));
    }

    public WebElement getSuccessDeletedCityPopUp(){
        return driver.findElement(By.xpath("//*[contains(text(), 'Deleted successfully')]"));
    }


    public WebElement getEditNewCityInput(){
        return driver.findElement(By.name("name"));
    }
    public void waitUntilShowSelectedNumberOfRows(int selectNumberOfRow){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
                        "//div[contains(@class, 'v-data-table__wrapper')]//tbody/tr"),
                selectNumberOfRow));
    }

    public WebElement getSelectedCell(int rowNumber, int cellNumber){
        return driver.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td[" + cellNumber + "]"));
    }

    public WebElement getEditButton(int rowNumber){
        return driver.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td//button[@id = 'edit']"));
    }

    public WebElement getDeleteButton(int rowNumber){
        return driver.findElement(By.xpath("//tbody/tr[" + rowNumber + "]/td//button[@id = 'delete']"));
    }
}