package org.example.tests;

import org.example.baseTests.BaseTests;
import org.example.utils.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@ExtendWith(AllureListener.class)
public class BankiRuParameterizedTest extends BaseTests {
    @ParameterizedTest
    @MethodSource("depositInf")
    @DisplayName("Проверка депозитного калькулятора")
    public void bankiTest(Parameters parameter) {
        DepositParameters depositParameters = parameter.getDepositParameters();
        Deposit deposit = parameter.getDeposit();

        app.getHomePage()
                .checkOpenPage()
                .contributionClick()
                .settingClick()
                .valueInput("Сумма", depositParameters.getDepositValue())
                .valueInput("Срок", depositParameters.getDepositPeriod())
                .valueInput("Тип вклада", depositParameters.getType())
                .valueInput("Банки", depositParameters.getBanks())
                .valueInput("Опции", depositParameters.getCheckboxes())
                .showResults()
                .checkResults("Процент", deposit)
                .checkResults("Срок", deposit)
                .checkResults("Доход", deposit);
    }
    public static Stream<Parameters> depositInf() {
        return Stream.of(
                new Parameters(
                        new DepositParameters("1 000 000", "6 месяцев", "Обычные вклады",
                                new String[]{"Тинькофф", "ВТБ", "Открытие", "Газпромбанк", "Сбербанк"},
                                new String[]{"Со снятием", "С пополнением", "С капитализацией"}),

                        "15", new Deposit("Тинькофф Банк", "5,63%", "182 дн.", "от 27 740 ₽")),
                new Parameters(
                        new DepositParameters("500 000", "2 года", "Детский",
                                new String[]{"Ак Барс Банк", "Банк «РОССИЯ»", "Сбербанк"},
                                new String[]{"С выплатой процентов"}),

                        "7", new Deposit("Сбербанк", "6,80%", "730 дн.", "от 72 514 ₽"))
        );
    }
}
