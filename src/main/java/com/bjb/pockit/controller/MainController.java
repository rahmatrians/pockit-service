package com.bjb.pockit.controller;


import com.bjb.pockit.constant.ResponseCode;
import com.bjb.pockit.constant.ResponseStatus;
import com.bjb.pockit.dtoWILLREMOVE.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")
public class MainController {
//    @Autowired
//    CurrencyService currencyService;
//
//    @Autowired
//    ExchangeRateService exchangeRateService;
//
//    @Autowired
//    TransationHistoryService transationHistoryService;
//
//
//
//    @GetMapping("/currencies")
//    public ResponseEntity<GetCurrienciesDto> getCurrencies(@RequestParam(value = "code", required = false) String code) {
//        GetCurrienciesDto response = currencyService.getAllCurrencies(code);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }
//
//    @GetMapping("/balances/{userProfileId}")
//    public ResponseEntity<GetListAccountBalanceDto> getBalance(@PathVariable Long userProfileId) {
//        GetListAccountBalanceDto response = currencyService.getAccountBalance(userProfileId);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }
//
//    @GetMapping("/balances/{userProfileId}/{accountNumber}")
//    public ResponseEntity<AccountBalanceDto> getBalance(@PathVariable Long userProfileId, @PathVariable String accountNumber) {
//        AccountBalanceDto response = currencyService.getAccountBalance(userProfileId, accountNumber);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }
//
//    @GetMapping("/history/{accountNumber}")
//    public ResponseEntity<TransactionHistoryDto> getTransactionHistory(@PathVariable String accountNumber) {
//        TransactionHistoryDto response = currencyService.getTransactionHistory(accountNumber);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }
//
//    @PostMapping("/forexrate")
//    public ResponseEntity<GetExchangeRateDto> getExchangeRate(@RequestBody ReqExchangeRateDto request) {
//        GetExchangeRateDto response = exchangeRateService.getExchangeRates(request);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }
//
//    @PostMapping("/transfer/execute")
//    public ResponseEntity<GetTransferDto> getTransferExeute(@RequestBody ReqTransferDto request) {
//        GetTransferDto response = transationHistoryService.executeTransfer(request);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }
//    @PutMapping("/update/currency")
//    public ResponseEntity<UpdateCurrencyDto> updateCurrency(@RequestBody UpdateCurrencyDto request) {
//        UpdateCurrencyDto response = currencyService.updateCurrency(request);
//
//        if (response.getRc().equals(ResponseCode.SUCCESS.getCode())) {
//            return ResponseEntity
//                    .status(ResponseStatus.OK.getStatus())
//                    .body(response);
//        } else {
//            return ResponseEntity
//                    .status(ResponseStatus.NOT_FOUND.getStatus())
//                    .body(response);
//        }
//    }


}
