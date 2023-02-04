package org.example.utils;
import lombok.Data;

@Data
public class Parameters {
    DepositParameters depositParameters;
    String count;
    Deposit deposit;

    public Parameters(DepositParameters depositParameters, String count, Deposit deposit) {
        this.depositParameters = depositParameters;
        this.count = count;
        this.deposit = deposit;
    }
}
