package com.WeekXII.challenger3.controller;

import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JsonplaceholderController {

    private PostService postService;

    public JsonplaceholderController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<Post> savePost(@PathVariable("postId") long postId) {
        Post savedPost = null;
        try {
            savedPost = postService.save(postId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/disable/{postId}")
    public ResponseEntity<Post> disablePost(@PathVariable("postId") long postId) {
        Post savedPost = null;
        try {
            savedPost = postService.disablePost(postId);
            return ResponseEntity.ok(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return postService.getAllPosts(pageNo, pageSize);
    }
}
