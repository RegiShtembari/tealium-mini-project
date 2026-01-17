package Login;

import Helpers.Screenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class login {
    private WebDriver driver;
    private WebDriverWait wait;
    private Helpers.SeleniumElements seleniumElements;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        seleniumElements = new Helpers.SeleniumElements(driver);
        ExtentSparkReporter spark = new ExtentSparkReporter("Files/Report/Login.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

    }


    @Test
    public void login() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test = extent.createTest("Test 1");
        driver.navigate().to("https://ecommerce.tealiumdemo.com/customer/account/login/");
        //email
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        element.sendKeys("test22@test.test");
        //password
        WebElement passwordfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
        passwordfield.sendKeys("test1234");
        //login button
//        WebElement login_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("send2")));
//        login_button.click();
        //check if i am logged in
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav")));
        assert success != null;
        if (success.isDisplayed()) {
            String path = Screenshot.captureScreenshot(driver, "LoginFailure");
            test.pass("I have logged in successfully",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else {
            // 1. Capture physical file
            String path = Screenshot.captureScreenshot(driver, "LoginFailure");

            // 2. Log to report and attach the image
            test.fail("Login failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }


    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (extent != null) {
                extent.flush();
            }
        }
    }

}
