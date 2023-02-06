package org.example.steps;

import io.cucumber.java.ru.И;
import org.example.managers.PageManager;

public class HomePageSteps {
    PageManager pageManager = PageManager.getPageManager();

    @И("Проверить корректность открытия страницы")
    public void checkOpenPage(){
        pageManager.getHomePage().checkOpenPage();
    }
    @И("Нажатие на кнопку вклады")
    public void contributionClick(){
        pageManager.getHomePage().contributionClick();
    }
}
