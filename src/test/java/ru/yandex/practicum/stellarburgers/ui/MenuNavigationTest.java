package ru.yandex.practicum.stellarburgers.ui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.stellarburgers.ui.pages.*;

public class MenuNavigationTest extends BasicSeleniumTestConfiguration {

    @Step("Авторизоваться под созданным пользователем и перейти в личный кабинет")
    private void loginAndGoToUserAccount() {
        new MainPage(driver).clickLoginAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillDataForAuthorization(testEmail, testPassword);
        loginPage.clickLoginButton();
        new MainPage(driver).clickUserAccountButton();
    }

    @Test
    @DisplayName("Переход по клику на 'Личный кабинет'")
    public void testNavigationToUserAccountViaLink() {
        loginAndGoToUserAccount();
        Assertions.assertTrue(new UserProfilePage(driver).checkLogoutButtonDisplayed(),
                "Личный кабинет пользователя не отобразился");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на 'Конструктор'")
    public void testNavigationToMainPageViaSectionName() {
        loginAndGoToUserAccount();
        new UserProfilePage(driver).clickBurgerConstructorHeaderLink();
        Assertions.assertTrue(new MainPage(driver).checkAssembleBurgerHeaderDisplayed(),
                "Клик по разделу 'Конструктор' не вернул на главную страницу сборки бургера");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void testNavigationToMainPageViaLogo() {
        loginAndGoToUserAccount();
        new UserProfilePage(driver).clickStellarBurgersLogo();
        Assertions.assertTrue(new MainPage(driver).checkAssembleBurgerHeaderDisplayed(),
                "Клик по логотипу Stellar Burgers не вернул на главную страницу сборки бургера");
    }
}
