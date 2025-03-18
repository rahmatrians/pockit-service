package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResTransactionDailyDTO {

    private Long userId;
    private Long page;
    private Long size;
    private List<TransactionDaily> transactions;
}
