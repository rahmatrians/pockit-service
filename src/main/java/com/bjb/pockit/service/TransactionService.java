package com.bjb.pockit.service;

import com.bjb.pockit.dto.*;
import com.bjb.pockit.entity.Transaction;
import com.bjb.pockit.entity.UserProfile;
import com.bjb.pockit.repository.PocketRepository;
import com.bjb.pockit.repository.TransactionRepository;
import com.bjb.pockit.util.DateTimeUtil;
import com.bjb.pockit.util.StringUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PocketRepository pocketRepository;



    @Transactional
    public ApiResponse<ResSummaryBalanceDTO> getSummaryBalance(Long userId, String date) {
        String message = "";
        ResSummaryBalanceDTO response = new ResSummaryBalanceDTO();

        try {
            LocalDate splitDate = LocalDate.parse(date);
            Long year = Long.valueOf(splitDate.getYear());
            Long month = Long.valueOf(splitDate.getMonthValue());

            TotalSummaryBalance totalTransaction = transactionRepository.findBalancebyUserId(userId, month, year);

            if (ObjectUtils.isEmpty(totalTransaction)) {
                message = "Failed to get total amount transaction";
                throw  new Exception(message);
            }

            TotalSummaryBalance pocketBalance = pocketRepository.findBalancebyUserId(userId);

            if (ObjectUtils.isEmpty(pocketBalance)) {
                message = "Failed to get total pocket balance";
                throw  new Exception(message);
            }

            response = ResSummaryBalanceDTO.builder()
                    .userId(userId)
                    .period(date)
                    .income(totalTransaction.getTotalIncome())
                    .expense(totalTransaction.getTotalExpense())
                    .balance(pocketBalance.getTotalBalance())
                    .build();

            message = "Total summary transaction and pocket balance is successfully";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResSummaryBalanceDTO>builder()
                .data(response)
                .message(message)
                .build();
    }


    @Transactional
    public ApiResponse<ResTransactionDailyDTO> getTransactionDaily(Long userId, String date, Long page, Long size) {
        String message = "";
        ResTransactionDailyDTO response = new ResTransactionDailyDTO();

        try {
            LocalDate splitDate = LocalDate.parse(date);
            Long year = Long.valueOf(splitDate.getYear());
            Long month = Long.valueOf(splitDate.getMonthValue());

            int defaultPage = Math.toIntExact((page != null) ? page : 0);
            int defaultSize = Math.toIntExact((size != null) ? size : 10);
            Pageable pageable = PageRequest.of(defaultPage, defaultSize);

            Page<TransactionDaily> dailyTransactions = transactionRepository.findTransactionDailyByUserId(userId, month, year, pageable);

            if (ObjectUtils.isEmpty(dailyTransactions)) {
                message = "Failed to get total daily transactions";
                throw  new Exception(message);
            }



            List<TransactionDailyDTO> transactionList = dailyTransactions.getContent()
                    .stream()
                    .map(transaction -> TransactionDailyDTO.builder()
                            .id(transaction.getId())
                            .transactionDate(transaction.getTransactionDate())
                            .description(transaction.getDescription())
                            .amount(transaction.getAmount())
                            .tag(transaction.getTag())
                            .pocket(transaction.getPocket())
                            .transactionType(StringUtil.setTransactionType(transaction.getTransactionType()))
                            .build()
                    ).toList();

            response = ResTransactionDailyDTO.builder()
                    .userId(userId)
                    .size(Long.valueOf(dailyTransactions.getSize()))
                    .page(Long.valueOf(dailyTransactions.getTotalPages()))
                    .transactions(transactionList)
                    .build();

            message = "daily transactions get successfully";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResTransactionDailyDTO>builder()
                .data(response)
                .message(message)
                .build();
    }


    @Transactional
    public ApiResponse<ResCreateTransactionDTO> createTransaction(ReqCreateTransactionDTO request) {
        String message = "";
        Transaction data = new Transaction();
        ResCreateTransactionDTO response = new ResCreateTransactionDTO();

        try {
            data.setUserProfileId(request.getUserId());
//            data.setSplitBillId();
            data.setTransactionType(request.getTransactionType());
            data.setPocketId(request.getPocketId());
//            data.setImage();
            data.setDescription(request.getDescription());
            data.setTag(request.getTag());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate transactionDate = LocalDate.parse(request.getDate(), formatter);
            data.setTransDate(transactionDate);
            data.setAmount(request.getAmount());
            data.setStatus(2); // 0 > unpaid || 1 > pending || 2 > paid
            data.setCreatedAt(DateTimeUtil.generateDateTimeIndonesia());

            Transaction transaction = transactionRepository.saveAndFlush(data);

            message = "daily transactions get successfully";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResCreateTransactionDTO>builder()
                .data(response)
                .message(message)
                .build();
    }

}
