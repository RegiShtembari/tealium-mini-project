package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private By qtyInput = By.xpath("(//input)[6]");
    private By updateBtn = By.xpath("(//button[@title='Update'])[1]");
    private By subtotalPrices = By.xpath("//td[@class='product-cart-total']//span[@class='price']");
    private By grandTotal = By.xpath("//strong//span[@class='price']");
    private By removeBtn = By.className("btn-remove");
    private By emptyMsg = By.xpath("//h1[text()='Shopping Cart is Empty']");
    private By cartRows = By.xpath("//table[@id='shopping-cart-table']//tbody//tr");
    private By deleteBtn = By.xpath(".//a[@title='Remove Item']");
    private By emptyMessage = By.xpath("//div[@class='cart-empty']/p[1]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void updateItemQty(int index, String qty) {
        wait.waitForElementClickable(By.xpath("(//span[contains(.,'Cart')])[1]")).click();
        actions.moveToElement(driver.findElement(By.id("newsletter"))).perform();
        wait.waitForElementClickable(By.xpath("//a[contains(.,'View Shopping Cart')]")).click();
        WebElement element = wait.waitForElementClickable(qtyInput);
        element.clear();
        element.sendKeys(qty);
        driver.findElement(updateBtn).click();
    }

    public double getItemsSum() {
        List<WebElement> elements = driver.findElements(subtotalPrices);
        return elements.stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replace("$", "").replace(",", "")))
                .sum();
    }

    public double getGrandTotal() {
        return Double.parseDouble(driver.findElement(grandTotal).getText().replace("$", "").replace(",", ""));
    }

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

    public List<WebElement> getCartRows() {

        return driver.findElements(cartRows);
    }

    public void deleteItem(int index) {
        // Find all remove buttons in the cart
        List<WebElement> removeButtons = driver.findElements(By.xpath("//td[contains(@class,'product-cart-remove')]//a"));

        if (!removeButtons.isEmpty() && index >= 0 && index < removeButtons.size()) {
            removeButtons.get(index).click();
            System.out.println("Clicked Remove Item at index: " + index);
        } else {
            System.out.println("No remove button found at index: " + index);
        }
    }


    public String getEmptyCartMessage() {
        return wait.waitForElementVisible(emptyMessage).getText().trim();
    }


}