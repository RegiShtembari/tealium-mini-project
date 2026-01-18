package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.TestListener;

@Listeners(utils.TestListener.class)
public class BaseTest {
    protected static WebDriver driver; // static to share across tests

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce.tealiumdemo.com/");

        // Handle cookie/privacy popups if present
        try {
            driver.findElement(By.id("privacy_pref_optin")).click();
            driver.findElement(By.id("consent_prompt_submit")).click();
        } catch (Exception e) {
            // popup not present, ignore
        }
    }

    public class TestData {
        public static String rememberedEmail;
        public static String rememberedPassword;
    }


    public void loginUser(String email, String pass) {
        HomePage home = new HomePage(driver);
        RegisterPage reg = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        home.clickAccount();
        home.clickLogin();
        loginPage.login(email, pass);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
