package ru.yandex.practicum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserProfilePage {
    private final WebDriverWait wait;

    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By constructorHeaderLink = By.xpath("//a[@href='/']//p[text()='Конструктор']");
    private final By stellarBurgersLogo = By.cssSelector("div[class*='header__logo'] a[href='/']");

    public UserProfilePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Нажать кнопку 'Выйти' в личном кабинете")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Нажать на ссылку 'Конструктор' в шапке")
    public void clickBurgerConstructorHeaderLink() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorHeaderLink)).click();
    }

    @Step("Нажать на логотип Stellar Burgers в шапке")
    public void clickStellarBurgersLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(stellarBurgersLogo)).click();
    }

    @Step("Проверить отображение кнопки 'Выйти' в личном кабинете")
    public boolean checkLogoutButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
    }
}
