package com.tonyprr.exchangerate.util;

import com.tonyprr.exchangerate.model.api.CurrencyExchangeRequest;
import com.tonyprr.exchangerate.model.api.CurrencyExchangeResponse;
import com.tonyprr.exchangerate.model.data.CurrencyConversion;

import java.math.MathContext;
import java.math.RoundingMode;

public class Utils {

    public static CurrencyExchangeResponse buildReponse(
            CurrencyExchangeRequest request,
            CurrencyConversion currencyConversion,
            ConversionTypeEnum conversionTypeEnum) {

        CurrencyExchangeResponse response = new CurrencyExchangeResponse();
        response.setAmount(request.getAmount());
        if (ConversionTypeEnum.DIRECT.equals(conversionTypeEnum)) {
            response.setChangeAmount(request.getAmount().multiply(currencyConversion.getExchangeRate()));
        } else if (ConversionTypeEnum.REVERSE.equals(conversionTypeEnum)) {
            response.setChangeAmount(request.getAmount()
                    .divide(currencyConversion.getExchangeRate(), MathContext.DECIMAL128)
                    .setScale(4, RoundingMode.HALF_UP)
            );
        }
        response.setChargeCurrency(request.getChargeCurrency());
        response.setDepositCurrency(request.getDepositCurrency());
        response.setExchangeRate(currencyConversion.getExchangeRate());
        return response;
    }
}
