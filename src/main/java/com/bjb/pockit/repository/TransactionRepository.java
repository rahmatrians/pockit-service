package com.bjb.pockit.repository;

import com.bjb.pockit.dto.TotalSummaryBalance;
import com.bjb.pockit.dto.TransactionDaily;
import com.bjb.pockit.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


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


    @Query(value = "SELECT " +
            " trx.id, " +
            " trx.description, " +
            " trx.tag, " +
            " trx.amount, " +
            " trx.trans_date AS transactionDate, " +
            " trx.transaction_type AS transactionType, " +
            " pkt.name AS pocket " +
            " FROM transaction trx " +
            " JOIN pocket pkt ON trx.pocket_id = pkt.id" +
            " WHERE trx.deleted_date IS NULL AND trx.user_profile_id = :userProfileId " +
            " AND EXTRACT(MONTH FROM trx.trans_date) = :month " +
            " AND EXTRACT(YEAR FROM trx.trans_date) = :year "
            , nativeQuery = true)
    Page<TransactionDaily> findTransactionDailyByUserId(
            @Param("userProfileId") Long userProfileId,
            @Param("month") Long month,
            @Param("year") Long year,
            Pageable pageable
    );
}
