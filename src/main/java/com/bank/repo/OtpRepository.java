package com.bank.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Otp;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByUserIdOrderByCreatedAtDesc(Long userId);
}