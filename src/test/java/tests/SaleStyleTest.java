package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class SaleStyleTest extends BaseTest {
    @Test
    public void testSaleStyles() {
        loginUser("testuser227@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("SALE", "View All Sale");

        ProductsPage products = new ProductsPage(driver);

        // Requirement: Strikethrough for old price
        Assert.assertTrue(products.getOldPriceStyle("text-decoration").contains("line-through"), "Old price is not strikethrough!");

        // Requirement: Blue color for special price
        String color = products.getSpecialPriceStyle("color");
        Assert.assertTrue(color.contains("0, 123, 181") || color.contains("blue"), "Special price is not blue!");
    }//bllue hex
}