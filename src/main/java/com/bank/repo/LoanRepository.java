package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Loan;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	List<Loan> findByUserIdOrderByCreatedAtDesc(Long userId);

}
