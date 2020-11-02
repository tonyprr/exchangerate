package com.tonyprr.exchangerate.model.api;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRateRequest {

    private String baseCurrencyCode;

    private String exchangeCurrencyCode;

    private BigDecimal exchangeRate;
}
