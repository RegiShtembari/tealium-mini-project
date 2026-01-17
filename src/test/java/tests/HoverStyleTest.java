package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        home.click_x();
        ProductsPage products = new ProductsPage(driver);
        products.hoverFirstProduct();

        // Assert style change - typically hover changes box-shadow, border, or opacity
        String shadow = products.getProductBoxStyle("border-color");
        Assert.assertNotEquals(shadow, "none", "Hover effect style (shadow) did not trigger!");
    }
}