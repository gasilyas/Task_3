package ru.yandex.practicum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInputField = By.cssSelector("input[name='name']");
    private final By passwordInputField = By.cssSelector("input[type='password']");
    private final By submitLoginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By restorePasswordLink = By.linkText("Восстановить пароль");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Заполнить поля для авторизации: Email = {email}, Пароль = {password}")
    public void fillDataForAuthorization(String email, String password) {
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(emailInputField));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(passwordInputField));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти' на форме логина")
    public void clickLoginButton() {
        driver.findElement(submitLoginButton).click();
    }

    @Step("Кликнуть по ссылке 'Зарегистрироваться'")
    public void clickRegistrationLinkOnLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Кликнуть по ссылке 'Восстановить пароль'")
    public void clickRestorePasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(restorePasswordLink)).click();
    }

    @Step("Проверить отображение кнопки 'Войти'")
    public boolean checkLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.elementToBeClickable(submitLoginButton)).isDisplayed();
    }
}
