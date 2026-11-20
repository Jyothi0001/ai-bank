package com.bank.services;



import org.springframework.stereotype.Service;

import com.bank.model.Loan;
import com.bank.repo.LoanRepository;

import java.util.List;
import java.util.Map;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final AiService aiService;

    public LoanService(
            LoanRepository loanRepository,
            AiService aiService) {

        this.loanRepository =
                loanRepository;

        this.aiService =
                aiService;
    }

    public List<Loan>
    getLoans(Long userId) {

        return loanRepository
                .findByUserIdOrderByCreatedAtDesc(
                        userId
                );
    }

    public Map<String, Object>
    predictLoan(
            Long userId,
            Double amount,
            Integer tenure,
            Double income,
            Integer creditScore) {

        Map<String, Object>
                prediction =
                    aiService.loanPrediction(
                        income,
                        creditScore,
                        amount
                    );

        Loan loan =
                new Loan();

        loan.setUserId(userId);

        loan.setAmount(amount);

        loan.setTenure(tenure);

        loan.setIncome(income);

        loan.setCreditScore(
                creditScore
        );

        Boolean eligible =
                (Boolean)
                    prediction.get(
                        "eligible"
                    );

        Double interestRate =
                (Double)
                    prediction.get(
                        "interestRate"
                    );

        loan.setEligibility(
                eligible
        );

        loan.setInterestRate(
                interestRate
        );

        loan.setStatus(
                Boolean.TRUE.equals(eligible)
                    ? "PENDING"
                    : "REJECTED"
        );

        loanRepository.save(loan);

        prediction.put(
                "loan",
                loan
        );

        return prediction;
    }

    public Loan updateStatus(
            Long loanId,
            String status) {

        Loan loan =
                loanRepository
                    .findById(loanId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Loan not found"
                        )
                    );

        loan.setStatus(status);

        return loanRepository.save(
                loan
        );
    }

    public List<Loan> getAllLoans() {

        return loanRepository.findAll();
    }
}