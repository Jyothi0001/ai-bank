package com.bank.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.BankTransaction;

import java.util.List;

public interface BankTransactionRepository
        extends JpaRepository<BankTransaction, Long> {

    List<BankTransaction> findByUserIdOrderByCreatedAtDesc(Long userId);
}