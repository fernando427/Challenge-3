package com.WeekXII.challenger3.controller;

import com.WeekXII.challenger3.exceptions.ValueAlreadyExistsException;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JsonplaceholderController {

    private PostService postService;

    public JsonplaceholderController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<Post> savePost(@PathVariable("postId") long postId) {
        Post savedPost = null;
        try {
            savedPost = postService.save(postId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
