package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners; // Added this
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.TestListener; // Added this

@Listeners(utils.TestListener.class) // This triggers the report for individual runs
public class BaseTest {
    // Declare as protected so child classes can see it
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce.tealiumdemo.com/");

        // Handling cookie/privacy popups
        driver.findElement(By.id("privacy_pref_optin")).click();
        driver.findElement(By.id("consent_prompt_submit")).click();
    }

    // This resolves the loginUser error in all test classes
    public void loginUser(String email, String pass) {
        HomePage home = new HomePage(driver);
        RegisterPage reg = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        home.clickAccount();
        home.clickLogin();
        loginPage.login(email, pass);
    }

    // Required for TestListener to capture screenshots
    public WebDriver getDriver() {
        return driver;
    }
}