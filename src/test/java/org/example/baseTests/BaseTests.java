package org.example.baseTests;

import org.example.managers.DriverManager;
import org.example.managers.InitManager;
import org.example.managers.PageManager;
import org.example.managers.TestPropManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.example.utils.PropConst.BASE_URL;

public class BaseTests {
    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    private TestPropManager propManager = TestPropManager.getTestPropManager();


    @BeforeAll
    public static void beforeAll(){
        InitManager.initFramework();
    }
    @BeforeEach
    public void beforeEach(){
        InitManager.initFramework();
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @AfterAll
    public static void afterAll(){
        InitManager.quitFramework();
    }
}
