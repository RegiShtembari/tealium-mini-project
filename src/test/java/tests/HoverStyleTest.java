package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.ProductsPage;

public class HoverStyleTest extends BaseTest {
    @Test
    public void testHoverEffect() {
        loginUser("testuser227@example.com", "Password123");

        HomePage home = new HomePage(driver);
        home.navigateToMenu("WOMEN", "View All Women");
        driver.findElement(By.id("tealiumlabs_retail-21_inquiry5667_closeButton")).click();

        ProductsPage products = new ProductsPage(driver);
        products.hoverFirstProduct();

        // Assert style change - typically hover changes box-shadow, border, or opacity
        String shadow = products.getProductBoxStyle("box-shadow");
        Assert.assertNotEquals(shadow, "none", "Hover effect style (shadow) did not trigger!");
    }
}