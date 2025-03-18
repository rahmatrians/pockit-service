package com.bjb.pockit.controller;

import com.bjb.pockit.constant.ResponseStatus;
import com.bjb.pockit.dto.*;
import com.bjb.pockit.service.UserProfileService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;



    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<ResRegisterDTO>> register(@RequestBody ReqRegisterDTO request) {

        ApiResponse<ResRegisterDTO> response = userProfileService.registerUser(request);

        if (ObjectUtils.isNotEmpty(response.getData())) {
            return ResponseEntity
                    .status(ResponseStatus.OK.getStatus())
                    .body(ApiResponse.success(response.getData(), response.getMessage()));
        } else {
            return ResponseEntity
                    .status(ResponseStatus.NOT_FOUND.getStatus())
                    .body(ApiResponse.error(response.getMessage()));
        }
    }


    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<ResLoginDTO>> login(@RequestBody ReqLoginDTO request) {
        ResLoginDTO test = ResLoginDTO.builder().build();
        return ResponseEntity.ok(ApiResponse.error("User found"));
    }
}
