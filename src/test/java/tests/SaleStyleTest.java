package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

import java.util.List;

public class SaleStyleTest extends BaseTest {

    @Test
    public void testEachProductHasBothPrices() {

        loginUser("testuser227@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("SALE", "View All Sale");
        home.click_x();
        ProductsPage products = new ProductsPage(driver);

        List<Boolean> priceChecks = products.areBothPricesDisplayedPerProduct();
        Assert.assertEquals(priceChecks.size(), 4, "Unexpected number of product cards");
        for (int i = 0; i < priceChecks.size(); i++) {
            Assert.assertTrue(priceChecks.get(i), "Bug: Product at index " + i + " does not show both prices");
        }
    }

//bllue hex
}