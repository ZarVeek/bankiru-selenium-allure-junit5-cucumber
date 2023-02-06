package org.example.page;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DepositsPage extends BasePage{
    @FindBy(xpath = "//button[@class=\"Button___sc-mcd2wg-2 gjpWjG\"]")
    private WebElement settings;

    @FindBy(xpath = "//div[@class=\"SearchModal__StyledBody-sc-wuz0ak-1 eQdrBU\"]//input")
    private List<WebElement> depositInputFields;

    @FindBy(xpath = "//div[@data-placement]//li")
    private List<WebElement> dropDownList;

    @FindBy(xpath = "//label[@class=\"Checkbox___sc-h4eidb-1 lmixWG\"]/span")
    private List<WebElement> depositCheckbox;

    @FindBy(xpath = "//button[@class=\"Button___sc-mcd2wg-2 EXrjP\"]")
    private WebElement showResults;

    @FindBy(xpath = "//div[@class=\"Text___sc-14s2757-0 kDxXVe\"]")
    private WebElement countResults;

    @FindBy (xpath = "//div[@class=\"FlexboxGrid___sc-fhs6fg-0 cLYEEl\"]")
    private List<WebElement> banks;


    @Step("Нажате на кнопку настроек")
    public DepositsPage settingClick(){
        waitUtilElementToBeClickable(settings).click();
        return this;
    }
    public DepositsPage depositValueInput(String value) {
        depositInputFields.stream()
                .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                        .getText().contains("Сумма"))
                .findAny()
                .get().sendKeys(value);
        waitStabilityPage(2000, 250);
        return this;
    }

    public DepositsPage depositPeriodChoosing(String period) {
        WebElement periodDropDownField = depositInputFields.stream()
                .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                        .getText().contains("Срок"))
                .map(element -> element.findElement(By.xpath(".//following-sibling::div[@data-test=\"dropdown\"]")))
                .findAny()
                .get();
        periodDropDownField.click();
        dropDownList.stream()
                .filter(element -> element.findElement(By.xpath("./div")).getText()
                        .contains(period))
                .findAny()
                .get()
                .click();
        waitStabilityPage(2000, 250);
        return this;
    }

    public DepositsPage depositTypeChoosing(String type) {
        WebElement depositTypeDropDownField = depositInputFields.stream()
                .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                        .getText().contains("Тип вклада"))
                .map(element -> element.findElement(By.xpath(".//following-sibling::div[@data-test=\"dropdown\"]")))
                .findAny()
                .get();
        depositTypeDropDownField.click();
        dropDownList.stream()
                .filter(element -> element.findElement(By.xpath("./div")).getText().contains(type))
                .findAny()
                .get()
                .click();
        waitStabilityPage(2000, 250);
        return this;
    }

    public DepositsPage bankChoosing(List<String> banks) {
        WebElement banksDropDownField = depositInputFields.stream()
                .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                        .getText().contains("Банки"))
                .map(element -> element.findElement(By.xpath(".//..")))
                .findAny()
                .get();
        banksDropDownField.click();
        for (String bankName : banks) {
            if (bankName != null) {
                WebElement bank = dropDownList.stream()
                        .filter(element -> element.getText().contains(bankName))
                        .findAny()
                        .get();
                bank.click();
            } else {
                break;
            }
        }
        banksDropDownField.click();
        waitStabilityPage(2000, 250);
        return this;
    }

    public DepositsPage additionalsChoosing(List<String> additionals) {
        for (String additional : additionals) {
            if (additional != null) {
                WebElement checkBox = depositCheckbox.stream()
                        .filter(element -> element.getText().contains(additional))
                        .findAny()
                        .get();
                checkBox.click();
            } else {
                break;
            }
        }
        waitStabilityPage(2000, 250);
        return this;
    }

    public DepositsPage showResults(){
        waitUtilElementToBeClickable(showResults).click();
        return this;
    }

    public DepositsPage checkSuitableDepositsAmount(String amount) {
        Assertions.assertEquals(amount,valueTextHandler(countResults.getText()),
                "Количество подходящих вкладов не соответствует " + amount);
        return this;
    }


    public DepositsPage checkRate(String bankName, String value){
        WebElement bank = findBank(bankName);
        Assertions.assertEquals(bank.findElement(By.xpath
                        ("./section/div/div/div/div/div/div[@class=\"styled__StyledDepositColumn-sc-y83ypp-0 kXNqNV\"]/div/div[@class=\"Text___sc-14s2757-0 eTCAlU\"]"))
                .getText(), value, "Процент не равен полученному");
        return this;
    }
    public DepositsPage checkPeriodChoosing(String bankName, String value){
        WebElement bank = findBank(bankName);
        Assertions.assertEquals(bank.findElement(By.xpath
                        ("./section/div/div/div/div/div/div[@class=\"styled__StyledDepositColumn-sc-y83ypp-0 faTYeO\"]/div/div[@class=\"Text___sc-14s2757-0 kLOrRe\"]"))
                .getText(), value, "Срок не равен полученному");
        return this;
    }
    public DepositsPage checkSalary(String bankName, String value){
        WebElement bank = findBank(bankName);
        Assertions.assertEquals(bank.findElement(By.xpath
                        ("./section/div/div/div/div/div/div[@class=\"styled__StyledDepositColumn-sc-y83ypp-0 gIhVqs\"]/div/div[@class=\"Text___sc-14s2757-0 kLOrRe\"]"))
                .getText(), value, "Доход не равен полученному");
        return this;
    }


    private WebElement findBank(String bankName) {
        for (WebElement bank: banks) {
            boolean check = bank.findElement(By.xpath
                            ("./section/div/div/div/div/div/div/div/div/div/div/div[@class=\"Text___sc-14s2757-0 gBOujG\"]"))
                    .getText().equals(bankName);
            if (check){
                System.out.println(bank.findElement(By.xpath
                                ("./section/div/div/div/div/div/div/div/div/div/div/div[@class=\"Text___sc-14s2757-0 gBOujG\"]"))
                        .getText());
                return bank;
            }
        }
        return Assertions.fail("банк не найден");
    }
}
