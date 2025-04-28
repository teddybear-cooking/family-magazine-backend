package com.arkar.family_magazine.service;

import com.arkar.family_magazine.model.Like;
import com.arkar.family_magazine.model.Post;
import com.arkar.family_magazine.model.User;
import com.arkar.family_magazine.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public Like likePost(User user, Post post) {
        // Check if already liked
        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);
        if (existingLike.isPresent()) {
            throw new IllegalStateException("User already liked this post.");
        }
        
        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();
        return likeRepository.save(like);
    }

    public Long countLikesForPost(Post post) {
        return likeRepository.countByPost(post);
    }

    public Optional<Like> findLikeByUserAndPost(User user, Post post) {
        return likeRepository.findByUserAndPost(user, post);
    }
}
