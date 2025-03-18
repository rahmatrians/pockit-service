package com.bjb.pockit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResLoginDTO {

    private Long id;
    private String fullname;
    private String gender;
    private String phoneNumber;
    private String email;
}
