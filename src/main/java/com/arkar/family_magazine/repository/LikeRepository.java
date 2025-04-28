package com.arkar.family_magazine.repository;

import com.arkar.family_magazine.model.Like;
import com.arkar.family_magazine.model.Post;
import com.arkar.family_magazine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {

    // Check if a user already liked a post
    Optional<Like> findByUserAndPost(User user, Post post);

    // Count likes for a post
    Long countByPost(Post post);
}
