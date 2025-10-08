package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

    public class ProductsPage {
        private final WebDriver driver;
        private final By sortDropdown = By.className("product_sort_container");
        private final By priceElements = By.className("inventory_item_price");

        public ProductsPage(WebDriver driver) {
            this.driver = driver;
        }

        public void sortByPriceLowToHigh() {
            Select select = new Select(driver.findElement(sortDropdown));
            select.selectByVisibleText("Price (low to high)");
        }

        public List<Double> getProductPrices() {
            List<WebElement> elements = driver.findElements(priceElements);
            List<Double> prices = new ArrayList<>();
            for (WebElement el : elements) {
                prices.add(Double.parseDouble(el.getText().replace("$", "").trim()));
            }
            return prices;
        }
    }




