package ru.yandex.practicum.stellarburgers.ui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.stellarburgers.ui.pages.*;

public class UserLoginTest extends BasicSeleniumTestConfiguration {

    private MainPage mainPage;

    @BeforeEach
    public void initPages() {
        mainPage = new MainPage(driver);
    }

    @Step("Ввести данные пользователя и нажать кнопку 'Войти'")
    private void executeLoginAndVerifySuccess(String errorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillDataForAuthorization(testEmail, testPassword);
        loginPage.clickLoginButton();

        Assertions.assertTrue(mainPage.checkAssembleBurgerHeaderDisplayed());
        Assertions.assertTrue(mainPage.checkOrderButtonDisplayed(), errorMessage);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void testLoginViaMainPageButton() {
        mainPage.clickLoginAccountButton();
        executeLoginAndVerifySuccess("Авторизация через главную страницу не удалась");
    }

    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет' в шапке страницы")
    public void testLoginViaUserAccountLink() {
        mainPage.clickUserAccountButton();
        executeLoginAndVerifySuccess("Авторизация через личный кабинет не удалась");
    }

    @Test
    @DisplayName("Вход по кнопке в форме регистрации")
    public void testLoginViaRegistrationPageLink() {
        mainPage.clickLoginAccountButton();
        new LoginPage(driver).clickRegistrationLinkOnLoginPage();
        new RegistrationPage(driver).clickLoginLinkOnRegistrationPage();
        executeLoginAndVerifySuccess("Авторизация со страницы регистрации не удалась");
    }

    @Test
    @DisplayName("Вход по кнопке в форме восстановления пароля")
    public void testLoginViaRestorePasswordPageLink() {
        mainPage.clickLoginAccountButton();
        new LoginPage(driver).clickRestorePasswordLink();
        new RestorePasswordPage(driver).clickLoginLinkOnRestorePasswordPage();
        executeLoginAndVerifySuccess("Авторизация со страницы восстановления пароля не удалась");
    }

    @Test
    @DisplayName("Выход по кнопке 'Выход' в личном кабинете")
    public void testLogoutFromUserAccountPage() {
        mainPage.clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillDataForAuthorization(testEmail, testPassword);
        loginPage.clickLoginButton();

        mainPage.clickUserAccountButton();
        new UserProfilePage(driver).clickLogoutButton();

        Assertions.assertTrue(loginPage.checkLoginButtonDisplayed(), "После выхода форма авторизации не появилась");
    }
}
