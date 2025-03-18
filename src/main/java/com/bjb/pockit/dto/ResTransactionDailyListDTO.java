package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResTransactionDailyListDTO {

    private Long id;
    private String description;
    private Double amount;
    private String transactionType;
    private String tag;
    private String transactionDate;
    private String pocket;
}
