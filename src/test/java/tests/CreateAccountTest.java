package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import org.testng.asserts.SoftAssert;
import tests.BaseTest;


public class CreateAccountTest extends BaseTest {

    @Test
    public void testCreateAccount() {
        HomePage home = new HomePage(driver);
        RegisterPage reg = new RegisterPage(driver);
        SoftAssert softAssert = new SoftAssert();

        home.clickAccount();
        home.clickRegister();

        // Validate page title
        softAssert.assertTrue(reg.getRegisterTitePage().contains("CREATE AN ACCOUNT"),
                "Title wrong! It is displayed: " + reg.getRegisterTitePage());

        // Use a unique email for every run
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "Password123";

        reg.fillForm("Test", "User", email, password);
        reg.rememberMeBtnClick();
        reg.submit();

        // Validate registration success
        softAssert.assertTrue(reg.getSuccessText().contains("Thank you for registering with Tealium Ecommerce."),
                "Registration failed!: " + reg.getSuccessText());

        // Store credentials to use in other tests
        TestData.rememberedEmail = email;
        TestData.rememberedPassword = password;

        // Logout
        home.clickAccount();
        home.logOut();

        softAssert.assertAll();
    }
}
