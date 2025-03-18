package com.bjb.pockit.repository;

import com.bjb.pockit.dto.TotalSummaryBalance;
import com.bjb.pockit.entity.Pocket;
import com.bjb.pockit.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PocketRepository extends JpaRepository<Pocket, Long> {

    @Query(value = "SELECT " +
            " SUM(balance) AS totalIncome, " +
            " SUM(balance) AS totalExpense, " +
            " SUM(balance) AS totalBalance " +
            " FROM pocket " +
            " WHERE deleted_date IS NULL AND user_profile_id = :userProfileId "
            , nativeQuery = true)
    TotalSummaryBalance findBalancebyUserId(
            @Param("userProfileId") Long userProfileId
    );

    @Query(value = " SELECT " +
            " p.* " +
            " FROM pocket p " +
            " WHERE deleted_date IS NULL AND user_profile_id = :userProfileId "
            , nativeQuery = true)
    Page<Pocket> findListPocketByUserId(
            @Param("userProfileId") Long userProfileId,
            Pageable pageable
    );
}
