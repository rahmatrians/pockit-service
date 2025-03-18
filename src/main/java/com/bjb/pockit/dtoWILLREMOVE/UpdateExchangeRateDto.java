package com.bjb.pockit.dtoWILLREMOVE;

import lombok.Data;

@Data
public class UpdateExchangeRateDto {
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private Double exchangeRate;
}
