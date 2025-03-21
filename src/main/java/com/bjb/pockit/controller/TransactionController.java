package com.bjb.pockit.controller;

import com.bjb.pockit.constant.ResponseStatus;
import com.bjb.pockit.dto.*;
import com.bjb.pockit.service.PocketService;
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

    @Autowired
    private PocketService pocketService;

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

    @GetMapping("/transaction")
    public ResponseEntity<ApiResponse<ResTransactionDailyDTO>> getTransactionDaily(@RequestParam("userId") Long userId, @RequestParam("date") String date, @RequestParam("page") Long page,  @RequestParam("size") Long size) {

        ApiResponse<ResTransactionDailyDTO> response = transactionService.getTransactionDaily(userId, date, page, size);

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

    @PostMapping("/transaction/create")
    public ResponseEntity<ApiResponse<ResCreateTransactionDTO>> createTransaction(@RequestBody ReqCreateTransactionDTO request) {

        ApiResponse<ResCreateTransactionDTO> response = transactionService.createTransaction(request);

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

    @GetMapping("/pocket")
    public ResponseEntity<ApiResponse<ResListPocketDTO>> getListPocket(@RequestParam("userId") Long userId, @RequestParam("page") Long page,  @RequestParam("size") Long size) {

        ApiResponse<ResListPocketDTO> response = pocketService.getListPocket(userId, page, size);

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

    @PutMapping("/transaction/update/{id}")
    public ResponseEntity<ApiResponse<ResCreateTransactionDTO>> updateTransaction(
            @PathVariable("id") Long id,
            @RequestBody ReqCreateTransactionDTO request) {


        ApiResponse<ResCreateTransactionDTO> response = transactionService.updateTransaction(id, request);

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

    @DeleteMapping("/transaction/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTransaction(@PathVariable("id") Long id) {
        ApiResponse<String> response = transactionService.deleteTransaction(id);

        if (response.getData() != null) {
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
