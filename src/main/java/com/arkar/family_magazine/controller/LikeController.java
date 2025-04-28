package com.arkar.family_magazine.controller;

import com.arkar.family_magazine.model.Like;
import com.arkar.family_magazine.model.Post;
import com.arkar.family_magazine.model.User;
import com.arkar.family_magazine.service.LikeService;
import com.arkar.family_magazine.service.PostService;
import com.arkar.family_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<String> likePost(@PathVariable UUID userId, @PathVariable UUID postId) {
        Optional<User> userOpt = userService.findById(userId);
        Optional<Post> postOpt = postService.findById(postId);

        if (userOpt.isEmpty() || postOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User or Post not found");
        }

        try {
            likeService.likePost(userOpt.get(), postOpt.get());
            return ResponseEntity.ok("Post liked successfully!");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> countLikes(@PathVariable UUID postId) {
        Optional<Post> postOpt = postService.findById(postId);
        if (postOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Long likeCount = likeService.countLikesForPost(postOpt.get());
        return ResponseEntity.ok(likeCount);
    }
}
