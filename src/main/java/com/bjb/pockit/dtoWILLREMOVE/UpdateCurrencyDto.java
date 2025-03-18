package com.bjb.pockit.dtoWILLREMOVE;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCurrencyDto {
    private Long id;
    private String code;
    private String description;

    private String rc;
    private String rcDescription;
}
