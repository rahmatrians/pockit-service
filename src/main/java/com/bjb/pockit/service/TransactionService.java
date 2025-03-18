package com.bjb.pockit.service;

import com.bjb.pockit.dto.*;
import com.bjb.pockit.entity.Pocket;
import com.bjb.pockit.entity.Transaction;
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
import java.util.Optional;

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

            Optional<Pocket> pocketTrans = pocketRepository.findById(transaction.getPocketId());

            Double balance = pocketTrans.get().getBalance();
            if (request.getTransactionType().equals(1) || request.getTransactionType().equals(3)) {
                balance = pocketTrans.get().getBalance() - request.getAmount();
            }else {
                balance = pocketTrans.get().getBalance() + request.getAmount();
            }

            pocketRepository.updateBalance(balance, request.getPocketId());

            response = ResCreateTransactionDTO.builder()
                    .userId(request.getUserId())
                    .pocketId(request.getPocketId())
                    .transactionType(request.getTransactionType())
                    .description(request.getDescription())
                    .tag(request.getDescription())
                    .amount(request.getAmount())
                    .date(request.getDate())
                    .build();

            message = "Transactions created successfully";

        }catch (Exception e) {
            response = null;
            log.error("Error : {}" + e.getMessage(), e);
        }

        return ApiResponse.<ResCreateTransactionDTO>builder()
                .data(response)
                .message(message)
                .build();
    }


    @Transactional
    public ApiResponse<ResCreateTransactionDTO> updateTransaction(Long id, ReqCreateTransactionDTO request) {
        String message = "";
        ResCreateTransactionDTO response = null;

        try {
            // Find the existing transaction
            Optional<Transaction> transactionOpt = transactionRepository.findById(id);

            if (transactionOpt.isEmpty()) {
                return ApiResponse.<ResCreateTransactionDTO>builder()
                        .data(null)
                        .message("Transaction not found with ID: " + id)
                        .build();
            }

            Transaction transaction = transactionOpt.get();

            // Store previous values for rollback
            Double prevAmount = transaction.getAmount();
            Long prevPocketId = transaction.getPocketId();
            Long prevTransType = transaction.getTransactionType();

            // 1. First rollback the previous pocket balance
            Optional<Pocket> prevPocketOpt = pocketRepository.findById(prevPocketId);

            if (prevPocketOpt.isEmpty()) {
                return ApiResponse.<ResCreateTransactionDTO>builder()
                        .data(null)
                        .message("Previous pocket not found with ID: " + prevPocketId)
                        .build();
            }

            Pocket prevPocket = prevPocketOpt.get();
            Double prevBalance = prevPocket.getBalance();

            // Reverse the previous transaction effect on the pocket balance
            if (prevTransType.equals(1L) || prevTransType.equals(3L) || prevTransType.equals(5L)) { // expense
                prevBalance += prevAmount; // Add back the expense amount
            } else if (prevTransType.equals(2L) || prevTransType.equals(4L)) { // income
                prevBalance -= prevAmount; // Subtract the income amount
            }

            // Update the previous pocket balance
            pocketRepository.updateBalance(prevBalance, prevPocketId);

            // 2. Update the transaction data
            transaction.setTransactionType(request.getTransactionType());
            transaction.setPocketId(request.getPocketId());
            transaction.setDescription(request.getDescription());
            transaction.setTag(request.getTag());

            // Parse and set the transaction date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate transactionDate = LocalDate.parse(request.getDate(), formatter);
            transaction.setTransDate(transactionDate);

            transaction.setAmount(request.getAmount());
            transaction.setUpdatedAt(DateTimeUtil.generateDateTimeIndonesia());

            // Save the updated transaction
            transaction = transactionRepository.saveAndFlush(transaction);

            // 3. Apply the new transaction to the new/same pocket
            Optional<Pocket> newPocketOpt = pocketRepository.findById(request.getPocketId());

            if (newPocketOpt.isEmpty()) {
                // If new pocket doesn't exist, roll back the previous pocket change and throw exception
                pocketRepository.updateBalance(prevPocket.getBalance(), prevPocketId);
                throw new IllegalArgumentException("New pocket not found with ID: " + request.getPocketId());
            }

            Pocket newPocket = newPocketOpt.get();
            Double newBalance = newPocket.getBalance();

            // Apply the new transaction effect to the pocket balance
            if (request.getTransactionType().equals(1L) || request.getTransactionType().equals(3L) || request.getTransactionType().equals(5L)) {
                newBalance -= request.getAmount(); // Subtract for expense
            } else {
                newBalance += request.getAmount(); // Add for income
            }

            // Update the new pocket balance
            pocketRepository.updateBalance(newBalance, request.getPocketId());

            // Build the response
            response = ResCreateTransactionDTO.builder()
                    .userId(request.getUserId())
                    .pocketId(request.getPocketId())
                    .transactionType(request.getTransactionType())
                    .description(request.getDescription())
                    .tag(request.getTag())
                    .amount(request.getAmount())
                    .date(request.getDate())
                    .build();

            message = "Transaction updated successfully";

        } catch (Exception e) {
            // Log the error and return null data
            log.error("Error updating transaction: {}", e.getMessage(), e);
            message = "Failed to update transaction: " + e.getMessage();
        }

        return ApiResponse.<ResCreateTransactionDTO>builder()
                .data(response)
                .message(message)
                .build();
    }

    @Transactional
    public ApiResponse<String> deleteTransaction(Long id) {
        String message = "";
        String result = null;

        try {
            // Find the transaction to delete
            Optional<Transaction> transactionOpt = transactionRepository.findById(id);

            if (transactionOpt.isEmpty()) {
                return ApiResponse.<String>builder()
                        .data(null)
                        .message("Transaction not found with ID: " + id)
                        .build();
            }

            Transaction transaction = transactionOpt.get();

            // Get transaction details needed for pocket balance adjustment
            Double amount = transaction.getAmount();
            Long transType = transaction.getTransactionType();
            Long pocketId = transaction.getPocketId();

            // Get the associated pocket
            Optional<Pocket> pocketOpt = pocketRepository.findById(pocketId);

            if (pocketOpt.isEmpty()) {
                return ApiResponse.<String>builder()
                        .data(null)
                        .message("Associated pocket not found with ID: " + pocketId)
                        .build();
            }

            Pocket pocket = pocketOpt.get();
            Double balance = pocket.getBalance();

            // Adjust the pocket balance by reversing the transaction effect
            if (transType.equals(1L) || transType.equals(3L) || transType.equals(5L)) { // expense
                balance += amount; // Add back the expense amount
            } else if (transType.equals(2L) || transType.equals(4L)) { // income
                balance -= amount; // Subtract the income amount
            }

            // Update the pocket balance
            pocketRepository.updateBalance(balance, pocketId);

            // Soft delete or hard delete based on your requirements
            // Option 1: Hard delete
            transactionRepository.deleteById(id);

            // Option 2: Soft delete (if your entity has a status field)
            // transaction.setStatus(0); // 0 for deleted/inactive
            // transaction.setUpdatedAt(DateTimeUtil.generateDateTimeIndonesia());
            // transactionRepository.save(transaction);

            result = "Transaction with ID: " + id;
            message = "Transaction deleted successfully";

        } catch (Exception e) {
            log.error("Error deleting transaction: {}", e.getMessage(), e);
            message = "Failed to delete transaction: " + e.getMessage();
        }

        return ApiResponse.<String>builder()
                .data(result)
                .message(message)
                .build();
    }

}
