package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Waits;

public class LoginPage {
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login with username: {user} and password: {pass}")
    public void login(String user, String pass) {
        Waits.visible(username).clear();
        Waits.visible(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
        // ✅ Wait until inventory page is loaded and menu button is clickable
        Waits.clickable(By.id("react-burger-menu-btn"));
    }

    @Step("Read login error message")
    public String getError() {
        return Waits.visible(errorMsg).getText();
    }




    }


