package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmail(){
        return driver.findElement(By.id("email"));
    }

    public WebElement getPassword(){
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton(){
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }
}