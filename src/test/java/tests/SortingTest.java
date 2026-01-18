package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

import java.util.List;

public class SortingTest extends BaseTest {

    @Test(groups = {"wishlistFlow"})
    public void testSortingAndWishlist() {
        loginUser("testuser227@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("WOMEN", "View All Women");
        home.click_x();
        //
        ProductsPage products = new ProductsPage(driver);
        products.sortByPrice();

        List<Double> prices = products.getAllDisplayedPrices();
        Assert.assertTrue(products.isSortedLowToHigh(prices), "Bug: Prices are not sorted from low to high");

        products.addMultipleProductsToWishlist(0, 1);


        String wishlistText = products.clickAccountAndGetWishlistText();
        System.out.println("Wishlist text after clicking Account: " + wishlistText);

        Assert.assertTrue(wishlistText.contains("My Wishlist (2 items)"),
                "Bug: 'My Wishlist' text not displayed after clicking Account");
    }
}
