package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDaily {

    private Long id;
    private String description;
    private String tag;
    private Double amount;
    private String transactionDate;
    private String pocket;
    private String transactionType;
}
