package com.bjb.pockit.repository;

import com.bjb.pockit.entity.UserAccountsWILLREMOVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountsWILLREMOVE, Integer> {
    boolean existsByCurrencyCode(String currencyCode);
}
