package org.example.steps;

import io.cucumber.java.bg.И;
import io.qameta.allure.Step;
import org.example.managers.PageManager;

import java.util.List;

public class DepositsPageStep {
    PageManager pageManager = PageManager.getPageManager();

    @И("Нажате на кнопку настроек")
    public void settingClick(){
        pageManager.getDepositsPage().settingClick();
    }
    @И("Заполнить поле \"Сумма\" значением {string}:")
    public void depositValueInput(String value) {
        pageManager.getDepositsPage().depositValueInput(value);
    }
    @И("Выбрать {string} в поле \"Срок\":")
    public void depositPeriodChoosing(String period) {
        pageManager.getDepositsPage().depositPeriodChoosing(period);
    }
    @И("Выбрать {string} в выпадающем списке поля \"Тип вклада\":")
    public void depositTypeChoosing(String type) {
        pageManager.getDepositsPage().depositTypeChoosing(type);
    }
    @И("Выбрать банки в выпадающем списке поля \"Банки\":")
    public void bankChoosing(List<String> banks) {
        pageManager.getDepositsPage().bankChoosing(banks);
    }
    @И("Выбрать дополнительные опции:")
    public void additionalsChoosing(List<String> additionals) {
        pageManager.getDepositsPage().additionalsChoosing(additionals);
    }
    @И("Нажать кнопку \"Показать\"")
    public void showResults(){
        pageManager.getDepositsPage().showResults();
    }
    @И("Проверить на равенство количество подходящих вкладов и ожидаемое значение {string}:")
    public void checkAmount(String amount){
        pageManager.getDepositsPage().checkSuitableDepositsAmount(amount);
    }
    @И("Проверить вклад банка {string} на соответствие процентной ставки значению {string}:")
    public void checkRate(String bankName, String value) {
        pageManager.getDepositsPage().checkRate(bankName, value);
    }

    @И("Проверить вклад банка {string} на соответствие срока значению {string}:")
    public void checkPeriodChoosing(String bankName, String value) {
        pageManager.getDepositsPage().checkPeriodChoosing(bankName, value);
    }

    @И("Проверить вклад банка {string} на соответствие дохода значению {string}:")
    public void checkSalary(String bankName, String value) {
        pageManager.getDepositsPage().checkSalary(bankName, value);
    }


}
