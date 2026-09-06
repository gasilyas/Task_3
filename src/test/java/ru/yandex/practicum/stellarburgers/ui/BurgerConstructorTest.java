package ru.yandex.practicum.stellarburgers.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.stellarburgers.ui.pages.MainPage;

public class BurgerConstructorTest extends BasicSeleniumTestConfiguration {

    private MainPage mainPage;

    @BeforeEach
    public void initPages() {
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Проверка переключения вкладок. Вкладка Булки в конструкторе бургеров")
    public void testBurgerAssemblerTabBunsSwitch() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        Assertions.assertTrue(mainPage.checkBunsTabActive(), "Вкладка 'Булки' не стала активной после переключения");
    }

    @Test
    @DisplayName("Проверка переключения вкладок. Вкладка Соусы в конструкторе бургеров")
    public void testBurgerAssemblerTabSaucesSwitch() {

        mainPage.clickSaucesTab();
        Assertions.assertTrue(mainPage.checkSaucesTabActive(), "Вкладка 'Соусы' не стала активной после переключения");
    }

    @Test
    @DisplayName("Проверка переключения вкладок. Вкладка Начинки в конструкторе бургеров")
    public void testBurgerAssemblerTabFillingsSwitch() {

        mainPage.clickFillingsTab();
        Assertions.assertTrue(mainPage.checkFillingsTabActive(), "Вкладка 'Начинки' не стала активной после переключения");
    }
}
