package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By email = By.id("email_address");
    private By password = By.id("password");
    private By confirmPass = By.id("confirmation");
    private By rememberBtn = By.xpath("//label[contains(.,'Remember Me')]");
    private By registerBtn = By.xpath("//button[@title='Register']");
    private By successMsg = By.className("success-msg");
    private By successTitle = By.cssSelector("div[class='page-title'] h1");
    private By logoutTitle = By.cssSelector("div[class='page-title'] h1");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String f, String l, String e, String p) {
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(email).sendKeys(e);
        driver.findElement(password).sendKeys(p);
        driver.findElement(confirmPass).sendKeys(p);
    }

    public void rememberMeBtnClick() {
        WebElement element = driver.findElement(rememberBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.click();
    }

    //kliko butonin per te krijuar account
    public void submit() {
        driver.findElement(registerBtn).click();
    }

    //mesazhi suksesit pas regjistrimit
    public String getSuccessText() {
        return wait.waitForElementVisible(successMsg).getText();
    }

    //mer titullin pasi shkon ne faqen e regjistrimit
    public String getRegisterTitePage() {
        return wait.waitForElementVisible(successTitle).getText();
    }

    //titulli ne logout
    public String getLogoutTitle() {
        return wait.waitForElementVisible(logoutTitle).getText();
    }
}