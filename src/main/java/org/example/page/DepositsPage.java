package org.example.page;

import io.qameta.allure.Step;
import org.example.utils.Deposit;
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
    @Step("Ввод/Выбор значения \"{value}\" в поле \"{fieldName}\"")
    public DepositsPage valueInput(String fieldName, String... value) {
        switch (fieldName) {
            case "Сумма":
                WebElement inputField = depositInputFields.stream()
                        .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                                .getText().contains(fieldName))
                        .findAny()
                        .get();
                inputField.sendKeys(value);
                break;
            case "Срок":
            case "Тип вклада":
                WebElement periodDropDownField = depositInputFields.stream()
                        .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                                .getText().contains(fieldName))
                        .map(element -> element.findElement(By.xpath(".//following-sibling::div[@data-test=\"dropdown\"]")))
                        .findAny()
                        .get();
                periodDropDownField.click();
                dropDownList.stream()
                        .filter(element -> element.findElement(By.xpath("./div")).getText()
                                .contains(value[0]))
                        .findAny()
                        .get()
                        .click();
                break;
            case "Банки":
                WebElement banksDropDownField = depositInputFields.stream()
                        .filter(element -> element.findElement(By.xpath(".//following-sibling::label"))
                                .getText().contains(fieldName))
                        .map(element -> element.findElement(By.xpath(".//..")))
                        .findAny()
                        .get();
                banksDropDownField.click();
                for (String bankName : value) {
                    WebElement bank = dropDownList.stream()
                            .filter(element -> element.getText().contains(bankName))
                            .findAny()
                            .get();
                    bank.click();
                }
                banksDropDownField.click();
                break;
            case "Опции":
                for (String option : value) {
                    WebElement checkBox = depositCheckbox.stream()
                            .filter(element -> element.getText().contains(option))
                            .findAny()
                            .get();
                    checkBox.click();
                }
                break;
            default:
                Assertions.fail("Поле " + fieldName + " отсутствует");
        }
        waitStabilityPage(5000, 250);
        return this;
    }
    @Step("Показать резульаты")
    public DepositsPage showResults(){
        waitUtilElementToBeClickable(showResults).click();
        return this;
    }
    @Step("Проверка значения")
    public DepositsPage checkResults(String fieldName, Deposit deposit){
        WebElement bank = findBank(deposit.getBankName());
        switch (fieldName){
            case("Процент"):
                Assertions.assertEquals(bank.findElement(By.xpath
                                ("./section/div/div/div/div/div/div[@class=\"styled__StyledDepositColumn-sc-y83ypp-0 kXNqNV\"]/div/div[@class=\"Text___sc-14s2757-0 eTCAlU\"]"))
                        .getText(), deposit.getRate(), "Процент не равен полученному");
                break;
            case ("Срок"):
                Assertions.assertEquals(bank.findElement(By.xpath
                                ("./section/div/div/div/div/div/div[@class=\"styled__StyledDepositColumn-sc-y83ypp-0 faTYeO\"]/div/div[@class=\"Text___sc-14s2757-0 kLOrRe\"]"))
                        .getText(), deposit.getPeriod(), "Срок не равен полученному");
                break;
            case ("Доход"):
                Assertions.assertEquals(bank.findElement(By.xpath
                                ("./section/div/div/div/div/div/div[@class=\"styled__StyledDepositColumn-sc-y83ypp-0 gIhVqs\"]/div/div[@class=\"Text___sc-14s2757-0 kLOrRe\"]"))
                        .getText(), deposit.getSalary(), "Доход не равен полученному");
                break;
        }
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
