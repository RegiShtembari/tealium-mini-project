package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.HomePage;
import pages.RegisterPage;

public class LoginTest extends BaseTest {

    @Test
    public void testSignIn() {
        SoftAssert softAssert = new SoftAssert();
        HomePage home = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage reg = new RegisterPage(driver);

        // 1. & 2. & 3. Navigate, click Account/Sign In, and Login
        // Note: loginUser is already defined in your BaseTest to handle navigation + login
        loginUser("testuser227@example.com", "Password123");
//        reg.rememberMeBtnClick();
        // 4. Check your username is displayed on right corner
        // The site usually shows "WELCOME, [NAME]!" in the header
        String headerText = loginPage.getHeaderText();
        softAssert.assertTrue(headerText.toLowerCase().contains("welcome"),
                "Username/Welcome message not found in the corner! Found: " + headerText);

        // Verification of Dashboard landing
        softAssert.assertTrue(loginPage.getWelcomeText().contains("MY DASHBOARD"),
                "Dashboard title not displayed!");

        // 5. Click on Account and Log Out
        home.clickAccount();
        home.logOut();

        // Check if we actually logged out (optional but good practice)
        softAssert.assertTrue(driver.getTitle().contains("Home page"), "Logout failed!");

        softAssert.assertAll();
    }
}