package com.bjb.pockit.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exchange_rates")
public class ExchangeRatesWILLREMOVE {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "from_currency_code")
    private String fromCurrencyCode;

    @Column(name = "to_currency_code")
    private String toCurrencyCode;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "rate_date")
    private String rateDate;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
}