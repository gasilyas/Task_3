package ru.yandex.practicum.stellarburgers.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By userAccountButton = By.cssSelector("a[href='/account']");
    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab')]/span[text()='Булки']");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab')]/span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab')]/span[text()='Начинки']");
    private final By activeBunsTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[text()='Булки']");
    private final By activeSaucesTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[text()='Соусы']");
    private final By activeFillingsTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span[text()='Начинки']");
    private final By assembleBurgerHeader = By.xpath("//h1[text()='Соберите бургер']");
    private final By submitOrderButton = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Нажать на кнопку 'Войти в аккаунт' на главной странице")
    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(userLoginButton)).click();
    }

    @Step("Нажать на кнопку 'Личный Кабинет' в шапке страницы")
    public void clickUserAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(userAccountButton)).click();
    }

    @Step("Нажать на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Нажать на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Нажать на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверить, активна ли вкладка 'Булки'")
    public boolean checkBunsTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeBunsTab)).isDisplayed();
    }

    @Step("Проверить, активна ли вкладка 'Соусы'")
    public boolean checkSaucesTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSaucesTab)).isDisplayed();
    }

    @Step("Проверить, активна ли вкладка 'Начинки'")
    public boolean checkFillingsTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeFillingsTab)).isDisplayed();
    }

    @Step("Проверить отображение заголовка 'Соберите бургер'")
    public boolean checkAssembleBurgerHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerHeader)).isDisplayed();
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean checkOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.elementToBeClickable(submitOrderButton)).isDisplayed();
    }
}
