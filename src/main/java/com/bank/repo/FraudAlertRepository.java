package com.bank.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.FraudAlert;

import java.util.List;

public interface FraudAlertRepository
        extends JpaRepository<FraudAlert, Long> {

    List<FraudAlert> findByStatus(String status);
}