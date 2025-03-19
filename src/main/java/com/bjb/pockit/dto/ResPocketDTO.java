package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResPocketDTO {

    private Long userId;
    private String name;
    private String accountNumber;
    private Double balance;
    private Long pocketTypeId;
}
