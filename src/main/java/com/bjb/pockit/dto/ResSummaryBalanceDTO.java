package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResSummaryBalanceDTO {

    private Long userId;
    private String period;
    private Double income;
    private Double expense;
    private Double balance;
}
