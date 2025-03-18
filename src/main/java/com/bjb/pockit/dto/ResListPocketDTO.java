package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResListPocketDTO {
    private Long id;
    private String name;
    private String accountNumber;
    private BigDecimal balance;
}
