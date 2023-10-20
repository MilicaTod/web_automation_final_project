package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.io.File;
import java.time.Duration;

public abstract class BasicTest {
    protected WebDriver driver;

    protected String baseUrl = "https://vue-demo.daniel-avellaneda.com";
    protected LoginPage loginPage;
    protected NavPage navPage;
    protected SignupPage signupPage;
    protected CitiesPage citiesPage;
    protected MessagePopUpPage messagePopUpPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        signupPage = new SignupPage(driver);
        navPage = new NavPage(driver);
        citiesPage = new CitiesPage(driver);
        loginPage = new LoginPage(driver);
        messagePopUpPage = new MessagePopUpPage(driver);

    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileHandler.copy(source, new File("src/main/java/screenshot/" + result.getName() + ".png"));
                System.out.println("Screenshot taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}