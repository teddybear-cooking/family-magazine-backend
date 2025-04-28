package com.arkar.family_magazine.service;

import com.arkar.family_magazine.model.Post;
import com.arkar.family_magazine.model.User;
import com.arkar.family_magazine.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> searchPostsByLocation(String location) {
        return postRepository.findByLocationContainingIgnoreCase(location);
    }

    public Optional<Post> findById(UUID postId) {
        return postRepository.findById(postId);
    }
}
