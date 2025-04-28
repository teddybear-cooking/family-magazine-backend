package com.arkar.family_magazine.repository;

import com.arkar.family_magazine.model.Post;
import com.arkar.family_magazine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    // Find all posts by a user
    List<Post> findByUser(User user);

    // Optional: find posts by location
    List<Post> findByLocationContainingIgnoreCase(String location);
}
