package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {
    private By qtyInput = By.xpath("//input[contains(@title, 'Qty')]");
    private By updateBtn = By.xpath("//button[@title='Update']");
    private By subtotalPrices = By.xpath("//td[@class='product-cart-total']//span[@class='price']");
    private By grandTotal = By.xpath("//strong//span[@class='price']");
    private By removeBtn = By.className("btn-remove");
    private By emptyMsg = By.xpath("//h1[text()='Shopping Cart is Empty']");

    public CartPage(WebDriver driver) { super(driver); }

    // Resolves error in CartTest
    public void updateFirstItemQty(String qty) {
        WebElement input = driver.findElement(qtyInput);
        input.clear();
        input.sendKeys(qty);
        driver.findElement(updateBtn).click();
    }

    // Resolves errors in CartTest
    public double getItemsSum() {
        List<WebElement> elements = driver.findElements(subtotalPrices);
        return elements.stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replace("$", "").replace(",", "")))
                .sum();
    }

    public double getGrandTotal() {
        return Double.parseDouble(driver.findElement(grandTotal).getText().replace("$", "").replace(",", ""));
    }

    // Resolves errors in EmptyCartTest
    public int getRowCount() {
        return driver.findElements(By.xpath("//table[@id='shopping-cart-table']/tbody/tr")).size();
    }

    public void removeFirstItem() {
        driver.findElement(removeBtn).click();
        driver.switchTo().alert().accept();
    }

    public boolean isEmptyMessageDisplayed() {
        return driver.findElement(emptyMsg).isDisplayed();
    }
}