package org.example.managers;

import org.example.page.DepositsPage;
import org.example.page.HomePage;

public class PageManager {
    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка депозита
     */
    private DepositsPage depositsPage;

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return StartPage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public DepositsPage getDepositsPage() {
        if (depositsPage == null) {
            depositsPage = new DepositsPage();
        }
        return depositsPage;
    }
}
