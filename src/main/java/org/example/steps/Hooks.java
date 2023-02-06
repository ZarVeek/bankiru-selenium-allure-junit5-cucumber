package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.managers.DriverManager;
import org.example.managers.InitManager;
import org.example.managers.TestPropManager;
import org.example.utils.PropConst;

public class Hooks {
    DriverManager driverManager = DriverManager.getInstance();
    TestPropManager testPropertiesManager = TestPropManager.getTestPropManager();
    @Before
    public void before() {
        InitManager.initFramework();
        driverManager.getDriver().get(testPropertiesManager.getProperty(PropConst.BASE_URL));
    }

    @After
    public void after() {
        InitManager.quitFramework();
    }
}
