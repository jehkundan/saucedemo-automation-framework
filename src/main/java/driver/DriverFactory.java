package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static WebDriver get() {
        return TL.get();
    }

    public static void init(String browser, boolean headless) {
        browser = browser == null ? "chrome" : browser.toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions co = new ChromeOptions();

                // ✅ Disable Chrome password manager, leak detection, save prompts
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("autofill.profile_enabled", false);
                prefs.put("autofill.credit_card_enabled", false);
                co.setExperimentalOption("prefs", prefs);

                // ✅ Disable Chrome security UI popups and services
                co.addArguments("--disable-save-password-bubble");
                co.addArguments("--disable-notifications");
                co.addArguments("--disable-infobars");
                co.addArguments("--no-default-browser-check");
                co.addArguments("--disable-password-manager-reauthentication");
                co.addArguments("--disable-automation-extension");
                co.addArguments("--disable-blink-features=AutomationControlled");
                co.addArguments("--password-store=basic");
                co.addArguments("--start-maximized");
                co.addArguments("--disable-features=PasswordManagerOnboarding,PasswordManagerService,AutofillServerCommunication");
                co.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());

                // ✅ Apply headless mode if requested
                if (headless) {
                    co.addArguments("--headless=new");
                    co.addArguments("--window-size=1920,1080");
                }

                // Create driver ONCE
                TL.set(new ChromeDriver(co));
                break;


            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions fo = new FirefoxOptions();

                // ✅ Disable Firefox password manager & autofill
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("signon.rememberSignons", false);          // Disable "Save password" popup
                profile.setPreference("signon.autofillForms", false);            // Disable autofill
                profile.setPreference("signon.autofillForms.http", false);
                profile.setPreference("signon.storeWhenAutocompleteOff", false);
                profile.setPreference("signon.management.page.breach-alerts.enabled", false); // Disable breach alerts
                profile.setPreference("extensions.formautofill.addresses.enabled", false);
                profile.setPreference("extensions.formautofill.creditCards.enabled", false);
                profile.setPreference("extensions.formautofill.heuristics.enabled", false);
                profile.setPreference("browser.formfill.enable", false);         // Disable form history
                profile.setPreference("browser.search.suggest.enabled", false);
                profile.setPreference("browser.urlbar.suggest.searches", false);
                profile.setPreference("security.insecure_field_warning.contextual.enabled", false);
                profile.setPreference("network.captive-portal-service.enabled", false);
                profile.setPreference("app.update.auto", false);
                profile.setPreference("dom.webnotifications.enabled", false);    // Disable push notifications
                profile.setPreference("privacy.trackingprotection.enabled", true);

                // ✅ Optional: run without first-run or default browser prompts
                profile.setPreference("browser.shell.checkDefaultBrowser", false);
                profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
                profile.setPreference("startup.homepage_welcome_url", "about:blank");

                fo.setProfile(profile);

                if (headless) {
                    fo.addArguments("-headless");
                    fo.addArguments("--width=1920");
                    fo.addArguments("--height=1080");
                }

                TL.set(new FirefoxDriver(fo));
                break;


            case "edge":
                WebDriverManager.edgedriver().setup();

                EdgeOptions eo = new EdgeOptions();

                // ✅ Disable password manager, leak detection, and autofill
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("credentials_enable_service", false);
                edgePrefs.put("profile.password_manager_enabled", false);
                edgePrefs.put("profile.password_manager_leak_detection", false);
                edgePrefs.put("autofill.profile_enabled", false);
                edgePrefs.put("autofill.credit_card_enabled", false);
                eo.setExperimentalOption("prefs", edgePrefs);

                // ✅ Disable Chrome/Edge UI popups and services
                eo.addArguments("--disable-save-password-bubble");
                eo.addArguments("--disable-notifications");
                eo.addArguments("--disable-infobars");
                eo.addArguments("--disable-password-manager-reauthentication");
                eo.addArguments("--no-default-browser-check");
                eo.addArguments("--disable-automation-extension");
                eo.addArguments("--disable-blink-features=AutomationControlled");
                eo.addArguments("--disable-features=PasswordManagerOnboarding,PasswordManagerService,AutofillServerCommunication");
                eo.addArguments("--password-store=basic");
                eo.addArguments("--start-maximized");
                eo.addArguments("--user-data-dir=/tmp/edge-profile-" + System.currentTimeMillis());

                // ✅ Optional: headless mode
                if (headless) {
                    eo.addArguments("--headless=new");
                    eo.addArguments("--window-size=1920,1080");
                }

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

