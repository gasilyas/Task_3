package ru.yandex.practicum.stellarburgers.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.stellarburgers.ui.pages.MainPage;

public class BurgerConstructorTest extends BasicSeleniumTestConfiguration {

    @Test
    @DisplayName("Проверка переключения вкладок в конструкторе бургеров: Булки, Соусы, Начинки")
    public void testBurgerAssemblerTabsSwitching() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();
        Assertions.assertTrue(mainPage.checkSaucesTabActive(), "Вкладка 'Соусы' не стала активной");

        mainPage.clickFillingsTab();
        Assertions.assertTrue(mainPage.checkFillingsTabActive(), "Вкладка 'Начинки' не стала активной");

        mainPage.clickBunsTab();
        Assertions.assertTrue(mainPage.checkBunsTabActive(), "Вкладка 'Булки' не стала активной после переключения");
    }
}
