package com.tonyprr.exchangerate;

import com.tonyprr.exchangerate.model.data.CurrencyConversion;
import com.tonyprr.exchangerate.repository.CurrencyConversionRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class ExchangeRateApplication {

	@Autowired
	private CurrencyConversionRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);

	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {

			repository.save(
					new CurrencyConversion("PEN-USD", "PEN", "USD", BigDecimal.valueOf(3.50), new Date(), null));
			repository.save(
					new CurrencyConversion("PEN-EUR", "PEN", "EUR", BigDecimal.valueOf(3.80), new Date(), null));

		};
	}
}
