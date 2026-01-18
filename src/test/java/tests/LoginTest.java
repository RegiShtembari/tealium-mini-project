package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.HomePage;
import pages.RegisterPage;
import tests.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testSignIn() {
        SoftAssert softAssert = new SoftAssert();
        HomePage home = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage reg = new RegisterPage(driver);
        loginUser("testuser227@example.com", "Password123");
        String headerText = loginPage.getHeaderText();
        softAssert.assertTrue(headerText.toLowerCase().contains("welcome"),
                "Username/Welcome message not found in the corner! Found: " + headerText);

        softAssert.assertTrue(loginPage.getWelcomeText().contains("MY DASHBOARD"),
                "Dashboard title not displayed!");

        // 5. Click on Account and Log Out
        home.clickAccount();
        home.logOut();

        // Check if logged out
        softAssert.assertTrue(driver.getTitle().contains("YOU ARE NOW LOGGED OUT"), "Logout text is not shown correctly!");

        softAssert.assertAll();
    }
}