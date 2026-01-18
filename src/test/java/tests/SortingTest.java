package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class SortingTest extends BaseTest {
    @Test
    public void testSortingAndWishlist() {
        loginUser("testuser@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("WOMEN", "View All Women");

        ProductsPage products = new ProductsPage(driver);
        products.sortByPrice();

        // Add two first products to wishlist
        products.addProductToWishlist(0);
        driver.navigate().back(); // Return from wishlist to product page
        products.addProductToWishlist(1);

        //asc or desc
    }
}