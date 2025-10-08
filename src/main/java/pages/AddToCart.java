package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCart {
    private final WebDriver driver;

    private final By boltTShirtAddBtn = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By cartIcon = By.className("shopping_cart_link");

    public AddToCart(WebDriver driver) {
        this.driver = driver;
    }

    public void addBoltTShirtToCart() {
        driver.findElement(boltTShirtAddBtn).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}
