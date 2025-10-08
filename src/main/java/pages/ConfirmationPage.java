package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ConfirmationPage {
    private final WebDriver driver;

    private final By headerMsg = By.className("complete-header");
    private final By bodyMsg = By.className("complete-text");
    private final By backHomeBtn = By.id("back-to-products");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyConfirmationMessage() {
        String expectedHeader = "Thank you for your order!";
        String expectedBody = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        String actualHeader = driver.findElement(headerMsg).getText();
        String actualBody = driver.findElement(bodyMsg).getText();

        Assert.assertEquals(actualHeader, expectedHeader, "❌ Header message is incorrect!");
        Assert.assertEquals(actualBody, expectedBody, "❌ Body message is incorrect!");
    }

    public void clickBackHome() {
        driver.findElement(backHomeBtn).click();
    }
}

