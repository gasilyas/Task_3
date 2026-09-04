package ru.yandex.practicum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInputField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInputField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInputField = By.cssSelector("input[type='password']");
    private final By submitRegistrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//p[contains(text(), 'Уже зарегистрированы?')]/a[text()='Войти']");
    private final By passwordErrorText = By.className("input__error");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Заполнить форму регистрации: Имя={name}, Email={email}, Пароль={password}")
    public void fillRegistrationForm(String name, String email, String password) {
        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(nameInputField));
        nameInput.clear();
        nameInput.sendKeys(name);

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(emailInputField));
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(passwordInputField));
        passwordInput.clear();
        passwordInput.sendKeys(password);

    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickSubmitRegistrationButton() {
        driver.findElement(submitRegistrationButton).click();
    }

    @Step("Кликнуть по ссылке 'Войти' на странице регистрации")
    public void clickLoginLinkOnRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверить отображение ошибки при вводе невалидного пароля")
    public boolean checkPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorText)).isDisplayed();
    }

}
