package org.example.page;

import org.example.managers.PageManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    @FindBy(xpath = "//h1[@class=\"le7ccaf0b\"]")
    WebElement title;

    @FindBy(xpath = "//a[@class=\"main-menu__sections-link\" and text() = \"Вклады\"]")
    WebElement contribution;

    public HomePage checkOpenPage(){
        Assertions.assertTrue(waitUtilElementToBeVisible(title).isDisplayed(), "Гланая старничка не открылась");
        return this;
    }
    public DepositsPage contributionClick(){
        contribution.click();
        return PageManager.getPageManager().getDepositsPage();
    }



}
