package ru.yandex.practicum.stellarburgers.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.stellarburgers.ui.factory.WebDriverFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BasicSeleniumTestConfiguration {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    protected final String testEmail = "test_login@yandex.ru";
    protected final String testPassword = "testPassword123";
    protected final String testName = "Tester";
    private String apiAccessToken;

    @BeforeEach
    public void setUp() {
        try { apiAccessToken = createUserViaApi(); } catch (Exception ignored) {}

        driver = WebDriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
        try { if (apiAccessToken != null) deleteUserViaApi(); } catch (Exception ignored) {}
    }

    private String createUserViaApi() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}", testEmail, testPassword, testName);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://qa-stellarburgers.education-services.ru/api/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        String respBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        int start = respBody.indexOf("Bearer");
        return start != -1 ? respBody.substring(start, respBody.indexOf("\"", start)) : null;
    }

    private void deleteUserViaApi() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://qa-stellarburgers.education-services.ru/api/auth/user"))
                .header("Authorization", apiAccessToken)
                .DELETE()
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
