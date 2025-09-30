package com.grp264.authdemo.service;

import com.grp264.authdemo.model.User;
import com.grp264.authdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register new user
    public void register(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        logger.info("User {} registered successfully", user.getUsername());
    }

    // Login check
    public Optional<User> login(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            String dbPassword = user.get().getPassword();
            logger.info("User {} logged in successfully", username);
            // Compare raw password with the hashed one
            if (passwordEncoder.matches(rawPassword, dbPassword)) {
                logger.info("User {} logged in successfully", username);
                return user;
            }
        }
        return Optional.empty();
    }
}
