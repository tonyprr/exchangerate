package com.tonyprr.exchangerate.controller;

import com.tonyprr.exchangerate.model.api.CurrencyExchangeRequest;
import com.tonyprr.exchangerate.model.api.CurrencyExchangeResponse;
import com.tonyprr.exchangerate.model.api.ExchangeRateRequest;
import com.tonyprr.exchangerate.model.data.CurrencyConversion;
import com.tonyprr.exchangerate.services.CurrencyConversionService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/exchangerate")
public class CurrencyConversionController {

    private final CurrencyConversionService service;

    @PatchMapping
    public Maybe<ResponseEntity<CurrencyConversion>> update(
            @RequestBody ExchangeRateRequest request) {

        return service.updateExchangeRate(request)
            .map(currencyConversion -> ResponseEntity.ok(currencyConversion))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flowable<CurrencyConversion> getAll() {
        return service.getAll();
    }

    @PostMapping(value = "/conversion")
    public Maybe<ResponseEntity<CurrencyExchangeResponse>> conversion(
            @RequestBody CurrencyExchangeRequest request) {

        return service.conversion(request)
                .map(currencyExchangeResponse -> ResponseEntity.ok(currencyExchangeResponse))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
