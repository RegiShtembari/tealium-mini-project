package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.actions = new Actions(driver);
    }
}