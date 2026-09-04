package ru.yandex.practicum.stellarburgers.ui.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        WebDriver driver;

        List<String> headlessArgs = List.of("--window-size=1920,1080", "--remote-allow-origins=*", "--no-sandbox", "--disable-dev-shm-usage");

        if ("yandex".equals(browser)) {
            ChromeOptions yandexOptions = new ChromeOptions();

            yandexOptions.addArguments(headlessArgs);
            yandexOptions.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");

            driver = new ChromeDriver(yandexOptions);
        } else {
            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.addArguments(headlessArgs);

            driver = new ChromeDriver(chromeOptions);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
