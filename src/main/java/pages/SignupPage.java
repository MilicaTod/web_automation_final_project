package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPage {
    private WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getName(){
        return driver.findElement(By.id("name"));
    }

    public WebElement getEmail(){
        return driver.findElement(By.id("email"));
    }

    public WebElement getPassword(){
        return driver.findElement(By.id("password"));
    }
    public WebElement getConfirmPassword(){
        return driver.findElement(By.id("confirmPassword"));
    }

    public WebElement getSignupButton(){
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }
}