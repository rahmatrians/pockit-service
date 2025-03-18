package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqCreateTransactionDTO {

    private Long userId;
    private String date;
    private Long transactionType;
    private Double amount;
    private String tag;
    private Long pocketId;
    private String description;
}
