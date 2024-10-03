package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String name);

    Optional<UserEntity> findByEmail(String email);

    @Query("""
            SELECT u FROM UserEntity u WHERE u.isActive = :isActive AND u.otpExpired < CURRENT_TIMESTAMP
            """)
    List<UserEntity> findByIsActiveAndOtpExpiredBefore(Short isActive);

}
