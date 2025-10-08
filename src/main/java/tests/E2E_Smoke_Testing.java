package tests;
import base.BaseTest;
import pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Epic("SauceDemo")
@Feature("Login")
public class E2E_Smoke_Testing extends BaseTest {

    @Story("Login > Add to cart > Checkout > Logout")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void logIn() {
        final String product = "Sauce Labs Backpack";
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");



    }

    @Story("Shorting Product on the basis of (Price high → low)")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void verifyPriceLowToHighFilter() {
        ProductsPage pPage = new ProductsPage(driver);
        pPage.sortByPriceLowToHigh();

        List<Double> actual = pPage.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected, "❌ Prices are not sorted low → high");
    }

    @Story("Place order for Bolt T-Shirt and verify confirmation message")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void testBoltTShirtOrderFlow() {

        String productName = "Sauce Labs Bolt T-Shirt";
        // ✅ Step 2: Products Page - Add item to cart
        AddToCart adCt = new AddToCart(driver);
        adCt.addBoltTShirtToCart();
        adCt.goToCart();

        // ✅ Step 3: Cart Page - Verify item and checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.verifyProductInCart(productName);
        cartPage.clickCheckout();

        // ✅ Step 4: Checkout Page - Fill info, verify product, finish
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInfo("Kundan", "Kumar", "560001");
        checkoutPage.verifyProductOnOverview(productName);
        checkoutPage.clickFinish();

        // ✅ Step 5: Confirmation Page - Validate messages & go home
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.verifyConfirmationMessage();
        confirmationPage.clickBackHome();
    }

    @Story("Logout flow")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void logOut() {

        LogOutPage logout = new LogOutPage(driver);
        logout.logOut();

        // Verify redirection to login page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo.com"),
                "Logout failed! User is not redirected to login page.");

    }


}
