package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Waits;

public class LogOutPage {

        private final WebDriver driver;
        private final By menuButton = By.id("react-burger-menu-btn");
        private final By logoutLink = By.id("logout_sidebar_link");

        public LogOutPage(WebDriver driver) {
            this.driver = driver;
        }

        @Step("Open side menu")
        public void openMenu() {
            System.out.println("Current URL at logout: " + driver.getCurrentUrl());
            Waits.clickable(menuButton).click();
        }
        @Step("Logout from the application")
        public void logOut() {
            openMenu();
            Waits.clickable(logoutLink).click();
        }

    }

