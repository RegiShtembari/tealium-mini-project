package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By emailField = By.id("email");
    private By passField = By.id("pass");
    private By loginBtn = By.id("send2");
    private By welcomeMsg = By.className("page-title"); // "My Dashboard" text

    // New locator for the username in the top right header
    private By headerUsername = By.cssSelector(".welcome-msg");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String pass) {
        // We use wait.waitForElementVisible to ensure fields are ready
        wait.waitForElementVisible(emailField).sendKeys(email);
        driver.findElement(passField).sendKeys(pass);
        RegisterPage reg = new RegisterPage(driver);
        reg.rememberMeBtnClick();
        driver.findElement(loginBtn).click();
    }

    public String getWelcomeText() {
        return wait.waitForElementVisible(welcomeMsg).getText();
    }

    // New method to fulfill Requirement #4
    public String getHeaderText() {
        return wait.waitForElementVisible(headerUsername).getText();
    }
}