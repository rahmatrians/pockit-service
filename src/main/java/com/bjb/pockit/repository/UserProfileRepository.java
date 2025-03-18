package com.bjb.pockit.repository;

import com.bjb.pockit.entity.UserAuthenticationsWILLREMOVE;
import com.bjb.pockit.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByEmail(String email);
}
