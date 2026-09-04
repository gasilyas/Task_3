package ru.yandex.practicum.stellarburgers.ui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.stellarburgers.ui.pages.*;

public class UserRegistrationTest extends BasicSeleniumTestConfiguration {

    private MainPage mainPage;
    private RegistrationPage registrationPage;

    @BeforeEach
    public void initializePages() {
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @Step("Перейти на страницу регистрации через главную страницу")
    private void navigateToRegistrationPageViaMainPage() {
        mainPage.clickLoginAccountButton();
        new LoginPage(driver).clickRegistrationLinkOnLoginPage();
    }

    @Step("Заполнить регистрационные данные пользователя и нажать кнопку регистрации")
    private void fillInAndSubmitRegistrationForm(String name, String email, String password) {
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickSubmitRegistrationButton();
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя с валидными данными")
    public void testSuccessfulRegistrationWithValidData() {
        navigateToRegistrationPageViaMainPage();

        String uniqueId = Long.toString(System.currentTimeMillis()).substring(8);
        String newEmail = "uiuser" + uniqueId + "@yandex.ru";
        String newPassword = "pass" + uniqueId;
        String newName = "UIuser" + uniqueId;

        fillInAndSubmitRegistrationForm(newName, newEmail, newPassword);

        LoginPage loginPage = new LoginPage(driver);
        Assertions.assertTrue(loginPage.checkLoginButtonDisplayed(), "Переход на форму авторизации не выполнен");
    }

    @Test
    @DisplayName("Проверка появления ошибки при регистрации с коротким паролем (меньше 6 символов)")
    public void testRegistrationWithShortPasswordShowsError() {
        navigateToRegistrationPageViaMainPage();

        String uniqueId = Long.toString(System.currentTimeMillis()).substring(8);
        String newEmail = "uishortuser" + uniqueId + "@yandex.ru";
        String newName = "UIshortuser" + uniqueId;

        fillInAndSubmitRegistrationForm(newName, newEmail, "12345");

        Assertions.assertTrue(registrationPage.checkPasswordErrorDisplayed(), "Ошибка 'Некорректный пароль' не появилась");
    }
}
