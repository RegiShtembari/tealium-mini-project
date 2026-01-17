package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

public class FilterTest extends BaseTest {
    @Test
    public void testBlackColorFilter() {
        loginUser("testuser@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("MEN", "View All Men");

        ProductsPage products = new ProductsPage(driver);
        products.filterByColor("Black");

        // Requirement: Check for blue border on the selected color swatch
        // Note: property might be 'outline' or 'border' depending on theme
        String border = products.getSpecialPriceStyle("outline");
        Assert.assertTrue(border.contains("0, 123, 181"), "Selected filter does not have a blue border!");

        //count
        //shikoje prp kte
        //checku cmimitr 0 =99
    }
}