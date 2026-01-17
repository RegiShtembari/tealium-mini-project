package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.WishlistPage;

public class CartTest extends BaseTest {
    @Test
    public void testCartCalculations() {
        loginUser("testuser@example.com", "Password123");

        driver.get("https://ecommerce.tealiumdemo.com/wishlist/");
        WishlistPage wishlist = new WishlistPage(driver);
        wishlist.moveAllToCart();

        CartPage cart = new CartPage(driver);
        cart.updateFirstItemQty("2");

        double expectedTotal = cart.getItemsSum();
        double actualTotal = cart.getGrandTotal();

        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Grand Total does not match sum of items!");
    }
}