package ru.yandex.practicum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RestorePasswordPage {
    private final WebDriverWait wait;

    private final By loginLink = By.xpath("//p[contains(text(), 'Вспомнили пароль')]/a[text()='Войти']");

    public RestorePasswordPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Кликнуть по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginLinkOnRestorePasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}
