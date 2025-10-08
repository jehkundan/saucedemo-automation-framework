package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

    public class CheckoutPage {
        private final WebDriver driver;

        private final By firstName = By.id("first-name");
        private final By lastName = By.id("last-name");
        private final By postalCode = By.id("postal-code");
        private final By continueBtn = By.id("continue");
        private final By productNameOnOverview = By.className("inventory_item_name");
        private final By finishBtn = By.id("finish");

        public CheckoutPage(WebDriver driver) {
            this.driver = driver;
        }

        public void fillCheckoutInfo(String fName, String lName, String zip) {
            driver.findElement(firstName).sendKeys(fName);
            driver.findElement(lastName).sendKeys(lName);
            driver.findElement(postalCode).sendKeys(zip);
            driver.findElement(continueBtn).click();
        }

        public void verifyProductOnOverview(String expectedProductName) {
            String actualName = driver.findElement(productNameOnOverview).getText();
            Assert.assertEquals(actualName, expectedProductName, "❌ Product name on checkout overview doesn't match!");
        }

        public void clickFinish() {
            driver.findElement(finishBtn).click();
        }
    }


