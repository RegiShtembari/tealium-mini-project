package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By accountBtn = By.xpath("//span[@class='label' and text()='Account']");
    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Log In");
    private By logoutLink = By.linkText("Log Out");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAccount() {
        wait.waitForElementClickable(accountBtn).click();
    }

    public void clickRegister() {
        wait.waitForElementClickable(registerLink).click();
    }

    public void clickLogin() {
        wait.waitForElementClickable(loginLink).click();
    }

    public void logOut() {
        wait.waitForElementClickable(logoutLink).click();
    }

    public void navigateToMenu(String mainCategory, String subCategory) {
        actions.moveToElement(driver.findElement(By.linkText(mainCategory))).perform();
        wait.waitForElementClickable(By.linkText(subCategory)).click();
    }
    public void click_x(){
        wait.waitForElementClickable(By.id("tealiumlabs_retail-21_inquiry5667_closeButton")).click();
    }
}