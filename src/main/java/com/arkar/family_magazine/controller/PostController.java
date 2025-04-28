package com.arkar.family_magazine.controller;

import com.arkar.family_magazine.model.Post;
import com.arkar.family_magazine.model.User;
import com.arkar.family_magazine.service.PostService;
import com.arkar.family_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable UUID userId, @RequestBody Post post) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        post.setUser(userOpt.get());
        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable UUID userId) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Post> posts = postService.findPostsByUser(userOpt.get());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String location) {
        List<Post> posts = postService.searchPostsByLocation(location);
        return ResponseEntity.ok(posts);
    }
}
