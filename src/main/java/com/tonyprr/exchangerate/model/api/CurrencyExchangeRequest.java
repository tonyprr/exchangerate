package com.tonyprr.exchangerate.model.api;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyExchangeRequest {

    private BigDecimal amount;

    private String chargeCurrency;

    private String depositCurrency;

}
