package org.example.page;

import org.example.managers.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class BasePage {
    private final DriverManager driverManager = DriverManager.getInstance();
    private final JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    protected WebElement clickToElementJs(WebElement element) {
        js.executeScript("arguments[0].click();", element);
        return element;
    }
    protected WebElement waitUtilElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected WebElement waitUtilElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void fillFields(WebElement element, String value){
        waitUtilElementToBeClickable(element).click();
        element.sendKeys(value);

    }
    protected void waitStabilityPage(int maxWaitMillis, int pollDelimiter){
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis()<startTime + maxWaitMillis){
            String prevState = driverManager.getDriver().getPageSource();
            wait(pollDelimiter);
            if(prevState.equals(driverManager.getDriver().getPageSource())){
                return;
            }
        }
    }
    private void wait(int mlSec){
        try {
            Thread.sleep(mlSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<String> toList(String value){
        return Arrays.asList(value.split(","));
    }

    protected String valueTextHandler(String guarantee) {
        return guarantee.replaceAll("\\D", "");
    }
}
