package com.bjb.pockit.repository;

import com.bjb.pockit.entity.TransactionHistoriesWILLREMOVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistoriesWILLREMOVE, Long> {
    List<TransactionHistoriesWILLREMOVE> findByFromUserAccountId(Long userAccountId);
}
