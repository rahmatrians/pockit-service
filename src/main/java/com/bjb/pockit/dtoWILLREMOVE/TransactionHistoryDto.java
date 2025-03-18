package com.bjb.pockit.dtoWILLREMOVE;

import com.bjb.pockit.entity.TransactionHistoriesWILLREMOVE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDto implements Serializable {
    private List<TransactionHistoriesWILLREMOVE> transactionHistories;

    private String rc;
    private String rcDescription;
}

