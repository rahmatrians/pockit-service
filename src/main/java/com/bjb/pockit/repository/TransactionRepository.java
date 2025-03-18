package com.bjb.pockit.repository;

import com.bjb.pockit.dto.TotalSummaryBalance;
import com.bjb.pockit.entity.Transaction;
import com.bjb.pockit.entity.TransactionHistoriesWILLREMOVE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT " +
            " SUM(CASE WHEN transaction_type IN (1, 3) THEN amount ELSE 0 END) AS totalIncome, " +
            " SUM(CASE WHEN transaction_type IN (2, 4) THEN amount ELSE 0 END) AS totalExpense, " +
            " SUM(CASE WHEN transaction_type IN (2, 4) THEN amount ELSE 0 END) AS totalBalance " +
            " FROM transaction " +
            " WHERE deleted_date IS NULL AND user_profile_id = :userProfileId " +
            " AND EXTRACT(MONTH FROM created_at) = :month " +
            " AND EXTRACT(YEAR FROM created_at) = :year "
            , nativeQuery = true)
    TotalSummaryBalance findBalancebyUserId(
            @Param("userProfileId") Long userProfileId,
            @Param("month") Long month,
            @Param("year") Long year
    );
}
