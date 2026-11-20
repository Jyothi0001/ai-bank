package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	 Optional<Account> findByUserId(Long userId);

	    Optional<Account> findByAccountNumber(String accountNumber);

}
