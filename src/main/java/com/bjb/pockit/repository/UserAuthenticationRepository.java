package com.bjb.pockit.repository;

import com.bjb.pockit.entity.UserAuthenticationsWILLREMOVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthenticationsWILLREMOVE, Long> {
    Optional<UserAuthenticationsWILLREMOVE> findByEmail(String email);
    UserAuthenticationsWILLREMOVE findByUserProfileId(Long userProfileId);
}