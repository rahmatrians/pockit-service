package com.bjb.pockit.repository;

import com.bjb.pockit.entity.ExchangeRatesWILLREMOVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRatesWILLREMOVE, Long> {
    ExchangeRatesWILLREMOVE findByFromCurrencyCodeAndToCurrencyCode(String fromCurrencyCode, String toCurrencyCode);
}
