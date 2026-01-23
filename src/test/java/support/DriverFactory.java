package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1400,900");

        if ("yandex".equals(browser)) {
            String yandexBinary = System.getProperty("yandex.binary", "").trim();
            if (!yandexBinary.isEmpty() && Files.exists(Path.of(yandexBinary))) {
                options.setBinary(yandexBinary);
            }
        }

        return new ChromeDriver(options);
    }
}
