package com.tonyprr.exchangerate.util;

import lombok.Getter;

@Getter
public enum ExchangeTypeEnum {

    PEN("PEN", "S/."),
    USD("USD", "$"),
    EURO("EUR", "€");

    private String code;
    private String symbol;

    ExchangeTypeEnum(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }
}
