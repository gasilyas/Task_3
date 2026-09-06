package ru.yandex.practicum.stellarburgers.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.stellarburgers.ui.factory.WebDriverFactory;

public class BasicSeleniumTestConfiguration {
    protected WebDriver driver;
    protected final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    protected final String testEmail = "test_login@yandex.ru";
    protected final String testPassword = "testPassword123";
    protected final String testName = "Tester";
    private String apiAccessToken;

    @BeforeEach
    public void setUpTests() {
        try { apiAccessToken = createUserViaApi(); } catch (Exception ignored) {}

        driver = WebDriverFactory.createDriver();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDownTests() {
        if (driver != null) driver.quit();
        try { if (apiAccessToken != null) deleteUserViaApi(); } catch (Exception ignored) {}
    }

    private String createUserViaApi() {
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}", testEmail, testPassword, testName);

        return io.restassured.RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("https://qa-stellarburgers.education-services.ru/api/auth/register")
                .path("accessToken");
    }

    private void deleteUserViaApi() {
        if (apiAccessToken != null) {
            io.restassured.RestAssured.given()
                    .header("Authorization", apiAccessToken)
                    .delete("https://qa-stellarburgers.education-services.ru/api/auth/user");
        }
    }
}
