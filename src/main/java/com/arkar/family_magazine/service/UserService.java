package com.arkar.family_magazine.service;

import com.arkar.family_magazine.model.User;
import com.arkar.family_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        // Optional: add validation logic here
        return userRepository.save(user);
    }

    public Optional<User> findByGmail(String gmail) {
        return userRepository.findByGmail(gmail);
    }

    public boolean existsByGmail(String gmail) {
        return userRepository.existsByGmail(gmail);
    }

    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }
}
