package com.bank.services;



import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.model.BankTransaction;
import com.bank.repo.AccountRepository;
import com.bank.repo.BankTransactionRepository;

import java.util.List;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final BankTransactionRepository transactionRepository;

    public BankService(
            AccountRepository accountRepository,
            BankTransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccount(Long userId) {

        return accountRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bank account not found"
                        )
                );
    }

    public Account deposit(
            Long userId,
            Double amount) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException(
                    "Invalid deposit amount"
            );
        }

        Account account =
                getAccount(userId);

        account.setBalance(
                account.getBalance() + amount
        );

        Account saved =
                accountRepository.save(account);

        saveTransaction(
                userId,
                account.getAccountNumber(),
                account.getAccountNumber(),
                amount,
                "DEPOSIT",
                "Cash deposit",
                "SUCCESS"
        );

        return saved;
    }

    public Account withdraw(
            Long userId,
            Double amount) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException(
                    "Invalid withdrawal amount"
            );
        }

        Account account =
                getAccount(userId);

        if (account.getBalance() < amount) {
            throw new RuntimeException(
                    "Insufficient balance"
            );
        }

        account.setBalance(
                account.getBalance() - amount
        );

        Account saved =
                accountRepository.save(account);

        saveTransaction(
                userId,
                account.getAccountNumber(),
                account.getAccountNumber(),
                amount,
                "WITHDRAW",
                "Cash withdrawal",
                "SUCCESS"
        );

        return saved;
    }

    public String transfer(
            Long userId,
            String receiverAccount,
            Double amount,
            String description,
            Boolean otpVerified) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException(
                    "Invalid transfer amount"
            );
        }

        Account sender =
                getAccount(userId);

        Account receiver =
                accountRepository
                        .findByAccountNumber(
                                receiverAccount
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Receiver account not found"
                                )
                        );

        if (sender.getAccountNumber()
                .equals(receiver.getAccountNumber())) {

            throw new RuntimeException(
                    "Cannot transfer to same account"
            );
        }

        if (sender.getBalance() < amount) {
            throw new RuntimeException(
                    "Insufficient balance"
            );
        }

        // OTP required for transfer
        if (otpVerified == null || !otpVerified) {

            return "OTP_REQUIRED";
        }

        sender.setBalance(
                sender.getBalance() - amount
        );

        receiver.setBalance(
                receiver.getBalance() + amount
        );

        accountRepository.save(sender);
        accountRepository.save(receiver);

        saveTransaction(
                userId,
                sender.getAccountNumber(),
                receiver.getAccountNumber(),
                amount,
                "TRANSFER",
                description,
                "SUCCESS"
        );

        return "TRANSFER_SUCCESS";
    }

    public List<BankTransaction> getTransactions(
            Long userId) {

        return transactionRepository
                .findByUserIdOrderByCreatedAtDesc(
                        userId
                );
    }

    private void saveTransaction(
            Long userId,
            String sender,
            String receiver,
            Double amount,
            String type,
            String description,
            String status) {

        BankTransaction transaction =
                new BankTransaction();

        transaction.setUserId(userId);
        transaction.setSenderAccount(sender);
        transaction.setReceiverAccount(receiver);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setStatus(status);
        transaction.setFraudScore(0.0);
        transaction.setFraudFlag(false);
        transaction.setLocation("Online");

        transactionRepository.save(transaction);
    }
}