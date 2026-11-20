package com.bank.services;



import org.springframework.stereotype.Service;

import com.bank.model.BankTransaction;
import com.bank.model.FraudAlert;
import com.bank.model.Loan;
import com.bank.model.User;
import com.bank.repo.BankTransactionRepository;
import com.bank.repo.FraudAlertRepository;
import com.bank.repo.LoanRepository;
import com.bank.repo.UserRepository;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    private final BankTransactionRepository
            transactionRepository;

    private final FraudAlertRepository
            fraudAlertRepository;

    private final LoanRepository loanRepository;

    public AdminService(
            UserRepository userRepository,
            BankTransactionRepository transactionRepository,
            FraudAlertRepository fraudAlertRepository,
            LoanRepository loanRepository) {

        this.userRepository =
                userRepository;

        this.transactionRepository =
                transactionRepository;

        this.fraudAlertRepository =
                fraudAlertRepository;

        this.loanRepository =
                loanRepository;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public List<BankTransaction>
    getTransactions() {

        return transactionRepository.findAll();
    }

    public List<FraudAlert>
    getFraudAlerts() {

        return fraudAlertRepository.findAll();
    }

    public List<Loan> getLoans() {

        return loanRepository.findAll();
    }

    public Loan updateLoanStatus(
            Long id,
            String status) {

        Loan loan =
                loanRepository
                    .findById(id)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Loan not found"
                        )
                    );

        loan.setStatus(status);

        return loanRepository.save(loan);
    }
}