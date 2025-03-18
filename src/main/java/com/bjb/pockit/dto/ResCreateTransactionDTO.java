package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResCreateTransactionDTO {

    private Long userId;
    private String date;
    private String transactionType;
    private Double amount;
    private String tag;
    private Long pocketId;
    private String description;
}
