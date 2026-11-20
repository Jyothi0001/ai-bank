package com.bank.services;



import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.bank.dto.LoginRequest;
import com.bank.dto.RegisterRequest;
import com.bank.model.Account;
import com.bank.model.User;
import com.bank.repo.AccountRepository;
import com.bank.repo.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository =
                userRepository;

        this.accountRepository =
                accountRepository;

        this.passwordEncoder =
                passwordEncoder;
    }

    public User register(
            RegisterRequest request) {

        if (request.getEmail() == null ||
            request.getEmail().isBlank()) {

            throw new RuntimeException(
                    "Email is required"
            );
        }

        if (request.getPassword() == null ||
            request.getPassword().isBlank()) {

            throw new RuntimeException(
                    "Password is required"
            );
        }

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }

        User user = new User();

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setMobile(
                request.getMobile()
        );

        user.setGender(
                request.getGender()
        );

        user.setLocation(
                request.getLocation()
        );

        user.setRole("USER");

        user.setActive(true);

        User savedUser =
                userRepository.save(user);

        Account account =
                new Account();

        account.setUserId(
                savedUser.getId()
        );

        account.setAccountNumber(
                generateAccountNumber()
        );

        account.setBalance(0.0);

        account.setAccountType(
                "SAVINGS"
        );

        account.setStatus(
                "ACTIVE"
        );

        accountRepository.save(account);

        return savedUser;
    }

    public Map<String, Object> login(
            LoginRequest request) {

        User user =
                userRepository
                    .findByEmail(
                            request.getEmail()
                    )
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Invalid email or password"
                        )
                    );

        if (!user.isActive()) {

            throw new RuntimeException(
                    "User account is inactive"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "message",
                "Login successful"
        );

        response.put(
                "user",
                user
        );

        response.put(
                "token",
                "DEMO-TOKEN-" +
                user.getId()
        );

        return response;
    }

    private String generateAccountNumber() {

        Random random =
                new Random();

        String accountNumber;

        do {

            long number =
                    1000000000L +
                    Math.abs(
                        random.nextLong()
                        % 9000000000L
                    );

            accountNumber =
                    String.valueOf(number);

        } while (
                accountRepository
                    .findByAccountNumber(
                            accountNumber
                    )
                    .isPresent()
        );

        return accountNumber;
    }
}