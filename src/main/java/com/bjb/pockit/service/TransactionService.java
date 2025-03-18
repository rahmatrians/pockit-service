package com.bjb.pockit.service;

import com.bjb.pockit.dto.*;
import com.bjb.pockit.entity.UserProfile;
import com.bjb.pockit.repository.PocketRepository;
import com.bjb.pockit.repository.TransactionRepository;
import com.bjb.pockit.util.DateTimeUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

}
