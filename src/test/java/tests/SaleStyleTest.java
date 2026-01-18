package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;

import java.util.List;

public class SaleStyleTest extends BaseTest {

    @Test
    public void testSaleProductsStyle() {
        // Step 0: Login
        loginUser("testuser227@example.com", "Password123");

        // Step 1: Navigate to SALE -> View All Sale
        HomePage home = new HomePage(driver);
        home.navigateToMenu("SALE", "View All Sale");
        home.click_x(); // close popup if any

        ProductsPage productsPage = new ProductsPage(driver);

        // Step 2: Find all sale products
        List<WebElement> products = driver.findElements(productsPage.productBoxAll());
        Assert.assertTrue(products.size() > 0, "No sale products found!");

        // Step 3: Loop through each product
        for (int i = 0; i < products.size(); i++) {
            WebElement product = products.get(i);

            // Check multiple prices exist
            boolean hasOldPrice = !product.findElements(productsPage.oldPrice()).isEmpty();
            boolean hasSpecialPrice = !product.findElements(productsPage.specialPrice()).isEmpty();
            Assert.assertTrue(hasOldPrice && hasSpecialPrice,
                    "Bug: Product at index " + i + " does not show both prices");

            // Old price checks
            if (hasOldPrice) {
                WebElement oldPriceElem = product.findElement(productsPage.oldPrice());
                String color = oldPriceElem.getCssValue("color");
                String textDecoration = oldPriceElem.getCssValue("text-decoration-line");

                // Check old price is grey ??
                Assert.assertTrue(colorMatchesHex(color, "#a0a0a0"),
                        "Bug: Old price color is not grey for product at index " + i);
                Assert.assertEquals(textDecoration, "line-through",
                        "Bug: Old price is not strikethrough for product at index " + i);
            }

            // Special price checks
            if (hasSpecialPrice) {
                WebElement specialPriceElem = product.findElement(productsPage.specialPrice());
                String color = specialPriceElem.getCssValue("color");
                String textDecoration = specialPriceElem.getCssValue("text-decoration-line");

                // Check special price is blue (#3399cc) and not strikethrough
                Assert.assertTrue(colorMatchesHex(color, "#3399cc"),
                        "Bug: Special price color is not blue for product at index " + i);
                Assert.assertNotEquals(textDecoration, "line-through",
                        "Bug: Special price should not be strikethrough for product at index " + i);
            }
        }
    }

    private boolean colorMatchesHex(String cssColor, String hexColor) {
        // Remove spaces
        cssColor = cssColor.replace(" ", "");

        int r = 0, g = 0, b = 0;

        if (cssColor.startsWith("rgba") || cssColor.startsWith("rgb")) {
            String[] parts = cssColor.replaceAll("rgba|rgb|\\(|\\)", "").split(",");
            r = Integer.parseInt(parts[0].trim());
            g = Integer.parseInt(parts[1].trim());
            b = Integer.parseInt(parts[2].trim());
        }

        String hex = String.format("#%02x%02x%02x", r, g, b);
        return hex.equalsIgnoreCase(hexColor);
    }
}
