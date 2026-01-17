package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class EmptyCartTest extends BaseTest {
    @Test
    public void testEmptyingCart() {
        loginUser("testuser@example.com", "Password123");

        driver.get("https://ecommerce.tealiumdemo.com/checkout/cart/");
        CartPage cart = new CartPage(driver);

        while (cart.getRowCount() > 0) {
            cart.removeFirstItem();
        }

        Assert.assertTrue(cart.isEmptyMessageDisplayed(), "Empty cart message not displayed!");
    }
}