package tests;
import base.BaseTest;
import pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("SauceDemo")
@Feature("Checkout")
public class E2E_SmokeTest extends BaseTest {

    @Story("Login > Add to cart > Checkout > Logout")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Happy path checkout")
    public void e2e_checkout_and_logout() {
        final String product = "Sauce Labs Backpack";

        new LoginPage(driver).login("standard_user", "secret_sauce");

    }
}


