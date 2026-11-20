package com.bank.services;



import org.springframework.stereotype.Service;

import com.bank.model.User;
import com.bank.repo.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository) {

        this.userRepository =
                userRepository;
    }

    public User getUser(Long id) {

        return userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User updateUser(
            Long id,
            User updatedUser) {

        User user =
                getUser(id);

        if (updatedUser.getName() != null) {

            user.setName(
                    updatedUser.getName()
            );
        }

        if (updatedUser.getMobile() != null) {

            user.setMobile(
                    updatedUser.getMobile()
            );
        }

        if (updatedUser.getGender() != null) {

            user.setGender(
                    updatedUser.getGender()
            );
        }

        if (updatedUser.getLocation() != null) {

            user.setLocation(
                    updatedUser.getLocation()
            );
        }

        return userRepository.save(user);
    }
}