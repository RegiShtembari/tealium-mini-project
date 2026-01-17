package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class WishlistPage extends BasePage {
    private By addToCartBtn = By.xpath("//button[@title='Add to Cart']");
    private By colorOption = By.xpath("//ul[@id='configurable_swatch_color']/li[1]/a");
    private By sizeOption = By.xpath("//ul[@id='configurable_swatch_size']/li[1]/a");
    private By finalAddBtn = By.xpath("//div[@class='add-to-cart-buttons']//button");

    public WishlistPage(WebDriver driver) { super(driver); }

    public void moveAllToCart() {
        List<WebElement> items = driver.findElements(addToCartBtn);
        for (int i = 0; i < items.size(); i++) {
            driver.findElements(addToCartBtn).get(0).click();
            wait.waitForElementClickable(colorOption).click();
            wait.waitForElementClickable(sizeOption).click();
            driver.findElement(finalAddBtn).click();
            driver.navigate().to("https://ecommerce.tealiumdemo.com/wishlist/");
        }
    }
}