package org.example.utils;

import lombok.Data;

@Data
public class Deposit {
    private String bankName;

    private String rate;

    private String period;

    private String salary;

    public Deposit(String bankName, String rate, String period, String profit) {
        this.bankName = bankName;
        this.rate = rate;
        this.period = period;
        this.salary = profit;
    }

    @Override
    public String toString() {
        return bankName;
    }
}