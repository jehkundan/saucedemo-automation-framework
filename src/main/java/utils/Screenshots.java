package utils;
import driver.DriverFactory;
import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public class Screenshots {
    public static Path take(String name) {
        try {
            File src = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
            Path outDir = Path.of("allure-results"); // Allure attachments dir ok
            Files.createDirectories(outDir);
            Path dest = outDir.resolve(name + "_" + Instant.now().toEpochMilli() + ".png");
            Files.copy(src.toPath(), dest);
            return dest;
        } catch (Exception e) {
            return null;
        }
    }
}

