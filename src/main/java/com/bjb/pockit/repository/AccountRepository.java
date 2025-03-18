package com.bjb.pockit.repository;

import com.bjb.pockit.entity.UserAccountsWILLREMOVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<UserAccountsWILLREMOVE, Long> {
    UserAccountsWILLREMOVE findByAccountNumber(String accountNumber);
    List<UserAccountsWILLREMOVE> findByUserProfileId(Long userProfileId);
    UserAccountsWILLREMOVE findByUserProfileIdAndAccountNumber(Long userProfileId, String accountNumber);
}