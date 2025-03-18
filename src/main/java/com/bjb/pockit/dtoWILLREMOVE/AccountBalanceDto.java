package com.bjb.pockit.dtoWILLREMOVE;

import com.bjb.pockit.entity.UserAccountsWILLREMOVE;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class AccountBalanceDto {
    private UserAccountsWILLREMOVE userAccount;

    private String rc;
    private String rcDescription;
}
