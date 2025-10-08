package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {
    private final WebDriver driver;

    private final By productName = By.className("inventory_item_name");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyProductInCart(String expectedProductName) {
        String actualProductName = driver.findElement(productName).getText();
        Assert.assertEquals(actualProductName, expectedProductName, "❌ Product name in cart does not match!");
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}

