package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishlistPage extends BasePage {
    private By addToCartBtn = By.xpath("//td//button[@title='Add to Cart']");
    private By colorOption = By.xpath("//ul[@id='configurable_swatch_color']/li[1]/a");
    private By sizeOption = By.xpath("//ul[@id='configurable_swatch_size']/li[1]/a");
    private By finalAddBtn = By.xpath("//div[@class='add-to-cart-buttons']//button");

    private String wishlistUrl = "https://ecommerce.tealiumdemo.com/wishlist/";

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public void moveAllToCart() {
        driver.get(wishlistUrl);

        List<WebElement> items = driver.findElements(addToCartBtn);
        int totalItems = items.size();

        for (int i = 0; i < totalItems; i++) {
            driver.findElements(addToCartBtn).get(0).click();
            // color
            actions.moveToElement(wait.waitForElementVisible(By.xpath("//a[contains(.,'Update Wishlist')]")))
                    .moveToElement(wait.waitForElementVisible(colorOption)).click().perform();

// size
            actions.moveToElement(wait.waitForElementVisible(By.xpath("//a[contains(.,'Update Wishlist')]")))
                    .moveToElement(wait.waitForElementVisible(sizeOption)).click().perform();

            actions.moveToElement(wait.waitForElementVisible(By.xpath("//h2[contains(.,'More Views')]")))
                    .moveToElement(wait.waitForElementVisible(finalAddBtn)).click().perform();

            // Navigate back to wishlist to continue with remaining items
            driver.get(wishlistUrl);
        }
    }
}
