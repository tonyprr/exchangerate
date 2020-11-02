package com.tonyprr.exchangerate.repository;

import com.tonyprr.exchangerate.model.data.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CurrencyConversionRepository
        extends JpaRepository<CurrencyConversion, String> {

    Optional<CurrencyConversion> findByBaseCurrencyCodeAndExchangeCurrencyCode(
            String baseCurrencyCode, String exchangeCurrencyCode
    );

}
