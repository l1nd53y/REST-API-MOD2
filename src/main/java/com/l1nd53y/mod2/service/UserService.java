package com.l1nd53y.mod2.service;

import com.l1nd53y.mod2.exception.ResourceNotFoundException;
import com.l1nd53y.mod2.model.User;
import com.l1nd53y.mod2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        UserRepository userRepository;

        PasswordEncoder passwordEncoder;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public User save(User user) {
            String hashedPassword = Hasher.hash(user.getPassword());
            user.setPassword(hashedPassword);
            return this.userRepository.save(user);
        }

    public User updateUser(long id, User userDetails) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + id));

        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmailId(userDetails.getEmailId());

        return userRepository.save(updateUser);
    }
    }

