package com.tonyprr.exchangerate.services;

import com.tonyprr.exchangerate.model.api.CurrencyExchangeRequest;
import com.tonyprr.exchangerate.model.api.CurrencyExchangeResponse;
import com.tonyprr.exchangerate.model.api.ExchangeRateRequest;
import com.tonyprr.exchangerate.model.data.CurrencyConversion;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface CurrencyConversionService {

    Maybe<CurrencyConversion> updateExchangeRate(ExchangeRateRequest request);

    Maybe<CurrencyExchangeResponse> conversion(CurrencyExchangeRequest request);

    Flowable<CurrencyConversion> getAll();
}
