package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

import java.util.List;

public class EmptyCartTest extends BaseTest {

    @Test
    public void testEmptyShoppingCart() {
        loginUser("testuser227@example.com", "Password123");

        // Navigate to cart page
        driver.get("https://ecommerce.tealiumdemo.com/checkout/cart/");

        CartPage cart = new CartPage(driver);

        // Get initial number of items
        List<WebElement> rows = cart.getCartRows();
        int totalItems = rows.size();

        while (totalItems > 0) {
            cart.deleteItem(0);
            driver.navigate().refresh();
            rows = cart.getCartRows();
            int newTotal = rows.size();

            Assert.assertEquals(newTotal, totalItems - 1,
                    "Bug: Item count did not decrease after deletion.");

            totalItems = newTotal;
        }

        String emptyMessage = cart.getEmptyCartMessage();
        Assert.assertEquals(emptyMessage, "You have no items in your shopping cart.",
                "Bug: Empty cart message not displayed correctly.");

        driver.quit();
    }
}
