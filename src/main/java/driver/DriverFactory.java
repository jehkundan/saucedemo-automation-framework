package driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static WebDriver get() { return TL.get(); }

    public static void init(String browser, boolean headless) {
        browser = browser == null ? "chrome" : browser.toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new","--window-size=1920,1080");
                TL.set(new ChromeDriver(co));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("-headless");
                TL.set(new FirefoxDriver(fo));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eo = new EdgeOptions();
                TL.set(new EdgeDriver(eo));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        get().manage().window().maximize();
    }

    public static void quit() {
        if (get() != null) {
            get().quit();
            TL.remove();
        }
    }
}
