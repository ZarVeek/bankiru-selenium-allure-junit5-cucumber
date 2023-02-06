package org.example.managers;

import org.example.utils.PropConst;

import java.util.concurrent.TimeUnit;

public class InitManager {
    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getInstance()
     */
    private static final DriverManager driverManager = DriverManager.getInstance();

    /**
     * Инициализация framework и запуск браузера со страницей приложения
     *
     * @see DriverManager
     * @see TestPropManager#getProperty(String)
     * @see org.example.utils.PropConst
     */
    public static void initFramework() {
        driverManager.getDriver().manage().timeouts()
                .pageLoadTimeout(Long.parseLong(props.getProperty(PropConst.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts()
                .implicitlyWait(Long.parseLong(props.getProperty(PropConst.IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().get(props.getProperty(PropConst.BASE_URL));
    }

    public static void quitFramework() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driverManager.quitDriver();
    }
}
