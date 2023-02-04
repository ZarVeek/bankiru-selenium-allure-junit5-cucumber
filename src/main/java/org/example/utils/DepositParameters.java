package org.example.utils;

import lombok.Data;

@Data
public class DepositParameters {

    private String depositValue;
    private String depositPeriod;
    private String type;
    private String[] banks;
    private String[] checkboxes;

    public DepositParameters(String depositValue, String depositPeriod, String type, String[] banks, String[] checkboxes) {
        this.depositValue = depositValue;
        this.depositPeriod = depositPeriod;
        this.type = type;
        this.banks = banks;
        this.checkboxes = checkboxes;
    }
}
