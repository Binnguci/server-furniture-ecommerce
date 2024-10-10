package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.InvalidatedTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedTokenEntity, Integer> {
}