package com.tonyprr.exchangerate.model.api;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyExchangeResponse {

    private BigDecimal amount;

    private BigDecimal changeAmount;

    private String chargeCurrency;

    private String depositCurrency;

    private BigDecimal exchangeRate;
}
