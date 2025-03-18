package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResRegisterDTO {

    private String fullname;
    private String gender;
    private String email;
    private String phoneNumber;
}
