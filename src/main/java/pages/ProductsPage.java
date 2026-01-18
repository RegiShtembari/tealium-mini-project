package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage extends BasePage {

    private WebDriver driver;
    private Actions actions;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    private By productBoxFirst = By.xpath("(//div[@class='category-products']//a)[3]"); // EXISTING

    private By allProducts = By.xpath("//ul/li[contains(@class,'item')]"); // MOCK

    private By wishlistBtn = By.xpath("//a[contains(.,'Add to Wishlist')]");
    private By colorFilterHeader = By.xpath("//dt[contains(.,'Type')]");

    private By sortSelect = By.xpath("(//select[@title='Sort By'])[1]");

    // PRICE LOCATORS (SALE)
    private By oldPrice = By.cssSelector(".old-price .price");

    private By specialPrice = By.cssSelector(".special-price .price");

    private By priceFilterDropdown = By.xpath("//div[contains(@class,'filter-price')]"); // MOCK

    private By firstPriceRangeOption = By.xpath("//a[contains(text(),'$0.00 - $99.99')]"); // MOCK

    private By selectColor = By.xpath("//img[@title='Black']");

    private By productPrice = By.xpath(".//span[contains(@class,'price')]"); // MOCK


    private By regularPrice = By.xpath(".//span[@class='price']");


    public void hoverFirstProduct() {
        actions.moveToElement(driver.findElement(productBoxFirst)).perform();
    }

    public void filterByColor() {

        WebElement color = driver.findElement(selectColor);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", color);
        actions.moveToElement(color).pause(200).click().perform();
    }


    public void sortByPrice() {
        driver.findElement(sortSelect).click();
        driver.findElement(By.xpath("(//option[contains(.,'Price')])[1]")).click();
    }


    public void selectPriceRange(String range) {
        driver.findElement(priceFilterDropdown).click();
        driver.findElement(firstPriceRangeOption).click();
    }

    public void goBack() {
        wait.waitForElementClickable(By.xpath("//a[contains(.,'here')]")).click();
    }

    public void addMultipleProductsToWishlist(int... indexes) {

        for (int i : indexes) {

            List<WebElement> products = driver.findElements(By.xpath("//li[contains(@class,'item')]"));
            WebElement productCard = products.get(i);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", productCard);
            actions.moveToElement(productCard).pause(500).perform();

            WebElement wishlistButton = productCard.findElement(By.linkText("Add to Wishlist"));
            actions.moveToElement(wishlistButton).click().perform();
            goBack();
            wait.waitForElementVisible(By.xpath("//li[contains(@class,'item')]"));
        }
    }

    public String clickAccountAndGetWishlistText() {
        WebElement wishlist = driver.findElement(By.xpath("//a//span[contains(.,'Account')]"));
        wishlist.click();
        WebElement wishlistLink = wait.waitForElementVisible(By.xpath("//a[contains(text(),'My Wishlist (2 items)')]"));

        return wishlistLink.getText().trim();
    }


    public List<WebElement> getAllProducts() {
        return driver.findElements(allProducts);
    }

    public By productBoxAll() {
        return allProducts;
    }

    public By oldPrice() {
        return oldPrice;
    }

    public By specialPrice() {
        return specialPrice;
    }

    public double getProductPrice(WebElement product) {
        String priceText = product.findElement(productPrice).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public String getProductBoxStyle(String property) {
        return driver.findElement(productBoxFirst).getCssValue(property);
    }

    public String getOldPriceStyle(String property) {
        return driver.findElement(oldPrice).getCssValue(property);
    }

    public String getSpecialPriceStyle(String property) {
        return driver.findElement(specialPrice).getCssValue(property);
    }

    public String getSelectedColorBorderColor() {
        actions.moveToElement(driver.findElement(By.xpath("//dt[contains(.,'Type')]"))).perform();
        WebElement colorElement = driver.findElement(By.xpath("//a[@name='black']"));
        return colorElement.getCssValue("border-color");
    }

    public boolean isBlue(String cssColor) {
        // rgba(51, 153, 204, 1) -> #3399cc
        cssColor = cssColor.replace(" ", "");

        int r = Integer.parseInt(cssColor.split("\\(")[1].split(",")[0]);
        int g = Integer.parseInt(cssColor.split(",")[1]);
        int b = Integer.parseInt(cssColor.split(",")[2].replace(")", ""));

        String hex = String.format("#%02x%02x%02x", r, g, b);
        return hex.equalsIgnoreCase("#3399cc");
    }

    public boolean isGrey(String cssColor) {
        // rgba(160, 160, 160, 1) -> #a0a0a0
        cssColor = cssColor.replace(" ", "");

        int r = Integer.parseInt(cssColor.split("\\(")[1].split(",")[0]);
        int g = Integer.parseInt(cssColor.split(",")[1]);
        int b = Integer.parseInt(cssColor.split(",")[2].replace(")", ""));

        String hex = String.format("#%02x%02x%02x", r, g, b);
        return hex.equalsIgnoreCase("#a0a0a0");
    }

    public boolean hasLineThrough(WebElement element) {
        String decoration = element.getCssValue("text-decoration-line");
        return decoration.equals("line-through");
    }


    public List<Boolean> areBothPricesDisplayedPerProduct() {
        List<WebElement> products = getAllProducts();
        List<Boolean> results = new ArrayList<>();

        for (WebElement product : products) {
            boolean hasOld = !product.findElements(oldPrice).isEmpty();
            boolean hasSpecial = !product.findElements(specialPrice).isEmpty();
            results.add(hasOld && hasSpecial);
        }
        return results;
    }

    public boolean trySelectPriceRange(String range) {
        try {
            driver.findElement(priceFilterDropdown).click();
            driver.findElement(firstPriceRangeOption).click();
            return true;
        } catch (ElementClickInterceptedException | NoSuchElementException | TimeoutException e) {

            System.out.println("INFO: Price filter not clickable. Test continues.");
            return false;
        }
    }

    public List<Double> getAllDisplayedPrices() {

        List<WebElement> productList = driver.findElements(allProducts);
        List<Double> prices = new ArrayList<>();

        for (WebElement product : productList) {

            WebElement priceElement;

            if (!product.findElements(specialPrice).isEmpty()) {
                priceElement = product.findElement(specialPrice);
            } else {
                priceElement = product.findElement(regularPrice);
            }

            String priceText = priceElement.getText(); // "$79.99"
            double price = Double.parseDouble(priceText.replace("$", "").trim());

            prices.add(price);
        }
        return prices;
    }

    public boolean isSortedLowToHigh(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
