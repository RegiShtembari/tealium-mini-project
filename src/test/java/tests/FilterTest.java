package tests;

import com.beust.ah.A;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

import java.util.List;

public class FilterTest extends BaseTest {

    @Test
    public void testPageFilters() {

        loginUser("testuser227@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("MEN", "View All Men");
        home.click_x();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.filterByColor();
        String borderColor = productsPage.getSelectedColorBorderColor();

        Assert.assertTrue(productsPage.isBlue(borderColor),
                "Bug: Selected black color filter does not have blue border"
        );


        boolean priceFilterApplied = productsPage.trySelectPriceRange("$0.00 - $99.99");
        if (!priceFilterApplied) {
            Assert.fail("WARNING: Price filter could not be applied. Skipping price-related checks.");
            return;
        }

        List<WebElement> products = productsPage.getAllProducts();
        Assert.assertEquals(
                products.size(),
                3,
                "Bug: Number of products is not 3 for price range $0.00 - $99.99"
        );

        for (int i = 0; i < products.size(); i++) {
            double price = productsPage.getProductPrice(products.get(i));
            Assert.assertTrue(
                    price >= 0 && price <= 99.99,
                    "Bug: Product price out of range. Price = " + price
            );
        }
    }
}
