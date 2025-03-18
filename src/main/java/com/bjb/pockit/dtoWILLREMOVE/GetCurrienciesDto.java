package com.bjb.pockit.dtoWILLREMOVE;

import com.bjb.pockit.entity.CurrenciesWILLREMOVE;
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
public class GetCurrienciesDto implements Serializable {
    private static final long serialVersionUID = 1614700481294176089L;

    private List<CurrenciesWILLREMOVE> currencies;

    private String rc;
    private String rcDescription;
}
