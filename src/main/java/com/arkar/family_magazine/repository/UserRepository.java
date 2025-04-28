package com.arkar.family_magazine.repository;

import com.arkar.family_magazine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Find user by Gmail (for login, etc.)
    Optional<User> findByGmail(String gmail);

    // Optional: check if gmail already exists
    boolean existsByGmail(String gmail);
}
