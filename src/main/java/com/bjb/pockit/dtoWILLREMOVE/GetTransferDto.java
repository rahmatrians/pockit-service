package com.bjb.pockit.dtoWILLREMOVE;

import com.bjb.pockit.entity.TransactionHistoriesWILLREMOVE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTransferDto implements Serializable {
    private static final long serialVersionUID = -15645866941142747L;

    private TransactionHistoriesWILLREMOVE transactionHistories;

    private String rc;
    private String rcDescription;
}
