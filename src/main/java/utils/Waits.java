package utils;
import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class Waits {
    private static long T() {
        return Long.parseLong(Config.get("explicit.wait"));
    }
    public static WebElement visible(By locator) {
        return new WebDriverWait(DriverFactory.get(), Duration.ofSeconds(T()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement clickable(By locator) {
        return new WebDriverWait(DriverFactory.get(), Duration.ofSeconds(T()))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
