package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import org.testng.asserts.SoftAssert;

public class CreateAccountTest extends BaseTest {
    @Test
    public void testCreateAccount() {
        HomePage home = new HomePage(driver);
        RegisterPage reg = new RegisterPage(driver);
        SoftAssert softAssert = new SoftAssert();

        home.clickAccount();
        home.clickRegister();

        softAssert.assertTrue(reg.getRegisterTitePage().contains("Creaeteee an Account"), "Title wrong! It is displayed: " + reg.getRegisterTitePage());
        // Use a unique email for every run
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        reg.fillForm("Test", "User", email, "Password123");
        reg.rememberMeBtnClick();
        reg.submit();
        //TODO:::::: change error title
        softAssert.assertTrue(reg.getSuccessText().contains("Thank you for registering with Tealsdfjnlsdfjlsdfjkldfjklsdfjkjklsfium Ecommerce.2"), "Registration failed!: " + reg.getSuccessText());

        home.clickAccount();
        home.logOut();
        //logout
//        softAssert.assertTrue(reg.getLogoutTitle().contains("test"), "Logout failed!");
        softAssert.assertAll();
    }
}