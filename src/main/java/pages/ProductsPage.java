package pages;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsPage extends BasePage {
    private By productBox = By.xpath("(//div[@class='category-products']//a)[3]");
    private By oldPrice = By.className("old-price");
    private By specialPrice = By.className("special-price");
    private By sortSelect = By.cssSelector("select[title='Sort By']");
    private By wishlistBtn = By.className("link-wishlist");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void hoverFirstProduct() {
        actions.moveToElement(driver.findElement(productBox)).perform();
    }

    public String getProductBoxStyle(String property) {
        return driver.findElement(productBox).getCssValue(property);
    }

    public String getOldPriceStyle(String property) {
        return driver.findElement(oldPrice).getCssValue(property);
    }

    public String getSpecialPriceStyle(String property) {
        return driver.findElement(specialPrice).getCssValue(property);
    }

    public void filterByColor(String color) {
        driver.findElement(By.xpath("//a[contains(@title, '" + color + "')]")).click();
    }

    public void sortByPrice() {
        driver.findElement(sortSelect).sendKeys("Price");
    }

    public void addProductToWishlist(int index) {
        List<WebElement> buttons = driver.findElements(wishlistBtn);
        buttons.get(index).click();
    }

    public List<Boolean> areBothPricesDisplayedPerProduct() {

        List<WebElement> products = driver.findElements(By.xpath("//ul//li[contains(@class,'item')]"));

        List<Boolean> results = new ArrayList<>();

        for (WebElement product : products) {

            boolean hasOldPrice = !product.findElements(By.xpath(".//p[contains(@class,'old-price')]")).isEmpty();

            boolean hasSpecialPrice = !product.findElements(By.xpath(".//p[contains(@class,'special-price')]")).isEmpty();

            results.add(hasOldPrice && hasSpecialPrice);
        }

        return results;
    }

    public List<Map<String, String>> getOldPriceStylesPerProduct() {

        List<WebElement> products = driver.findElements(By.xpath("//li[contains(@class,'item')]"));

        List<Map<String, String>> styles = new ArrayList<>();

        for (WebElement product : products) {

            WebElement oldPrice = product.findElement(By.xpath(".//p[contains(@class,'old-price')]"));

            Map<String, String> styleMap = new HashMap<>();
            styleMap.put("color", oldPrice.getCssValue("color"));
            styleMap.put("textDecoration", oldPrice.getCssValue("text-decoration"));

            styles.add(styleMap);
        }

        return styles;
    }


}