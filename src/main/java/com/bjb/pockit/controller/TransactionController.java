package com.bjb.pockit.controller;

import com.bjb.pockit.constant.ResponseStatus;
import com.bjb.pockit.dto.*;
import com.bjb.pockit.service.TransactionService;
import com.bjb.pockit.service.UserProfileService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;



    @GetMapping("/balance")
    public ResponseEntity<ApiResponse<ResSummaryBalanceDTO>> getBalance(@RequestParam("userId") Long userId, @RequestParam("date") String date) {

        ApiResponse<ResSummaryBalanceDTO> response = transactionService.getSummaryBalance(userId, date);

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
}
