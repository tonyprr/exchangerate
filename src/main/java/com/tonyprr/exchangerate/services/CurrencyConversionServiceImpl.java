package com.tonyprr.exchangerate.services;

import com.tonyprr.exchangerate.model.api.CurrencyExchangeRequest;
import com.tonyprr.exchangerate.model.api.CurrencyExchangeResponse;
import com.tonyprr.exchangerate.model.api.ExchangeRateRequest;
import com.tonyprr.exchangerate.model.data.CurrencyConversion;
import com.tonyprr.exchangerate.repository.CurrencyConversionRepository;
import com.tonyprr.exchangerate.util.ConversionTypeEnum;
import com.tonyprr.exchangerate.util.Utils;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    private final CurrencyConversionRepository repository;

    @Override
    public Maybe<CurrencyConversion> updateExchangeRate(ExchangeRateRequest request) {
        return Single.just(repository.findByBaseCurrencyCodeAndExchangeCurrencyCode(
                request.getBaseCurrencyCode(), request.getExchangeCurrencyCode()))
                .flatMapMaybe(currencyConversionOpt -> {
                    if (currencyConversionOpt.isPresent()) {
                        CurrencyConversion currencyConversion =
                                currencyConversionOpt.get();
                        currencyConversion.setExchangeRate(request.getExchangeRate());
                        currencyConversion.setModifiedAt(new Date());
                        return Maybe.just(repository.save(currencyConversion));
                    } else {
                        return Maybe.empty();
                    }
                });

    }

    @Override
    public Maybe<CurrencyExchangeResponse> conversion(CurrencyExchangeRequest request) {
        return Single.just(repository.findByBaseCurrencyCodeAndExchangeCurrencyCode(
                request.getChargeCurrency(), request.getDepositCurrency()))
                .flatMapMaybe(currencyConversionOpt -> {
                    if (currencyConversionOpt.isPresent()) {

                        CurrencyConversion currencyConversion =
                                currencyConversionOpt.get();
                        return Maybe.just(Utils.buildReponse(request, currencyConversion, ConversionTypeEnum.DIRECT));
                    } else {

                        return Single.just(repository.findByBaseCurrencyCodeAndExchangeCurrencyCode(
                                request.getDepositCurrency(), request.getChargeCurrency()))
                                .flatMapMaybe(currencyConversionOpt2 -> {
                                    if (currencyConversionOpt2.isPresent()) {

                                        CurrencyConversion currencyConversion =
                                                currencyConversionOpt2.get();
                                        return Maybe.just(Utils.buildReponse(request, currencyConversion, ConversionTypeEnum.REVERSE));
                                    } else {
                                        return Maybe.empty();
                                    }
                                });
                    }
                });
    }

    @Override
    public Flowable<CurrencyConversion> getAll() {
        return Flowable.fromIterable(repository.findAll());
    }
}
