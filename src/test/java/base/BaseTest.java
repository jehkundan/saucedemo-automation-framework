package base;
import driver.DriverFactory;
import utils.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browser) {
        String b = browser != null ? browser : Config.get("browser");
        boolean headless = Boolean.parseBoolean(Config.get("headless"));
        DriverFactory.init(b, headless);
        driver = DriverFactory.get();
        driver.get(Config.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quit();
    }
}


