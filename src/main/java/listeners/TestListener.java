package listeners;

import io.qameta.allure.Attachment;
import org.testng.*;
import utils.Screenshots;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot("FAIL_" + result.getMethod().getMethodName());
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] attachScreenshot(String name) {
        try {
            Path p = Screenshots.take(name);
            if (p != null) return Files.readAllBytes(p);
        } catch (Exception ignored) {}
        return new byte[0];
    }
}

