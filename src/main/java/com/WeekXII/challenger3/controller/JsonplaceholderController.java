package com.WeekXII.challenger3.controller;

import com.WeekXII.challenger3.model.Comment;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.services.CommentService;
import com.WeekXII.challenger3.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JsonplaceholderController {

    private PostService postService;
    private CommentService commentService;

    public JsonplaceholderController(PostService postService,
                                     CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/processPost/{postId}")
    public ResponseEntity<Post> processPost(@PathVariable("postId") long postId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.processPost(postId));
    }

    @PostMapping("/disable/{postId}")
    public ResponseEntity<Post> disablePost(@PathVariable("postId") long postId) {
        return ResponseEntity.ok(postService.disablePost(postId));
    }

    @PutMapping("/reprocessPost/{postId}")
    public ResponseEntity<Post> reprocessPost(@PathVariable("postId") long postId) {
        return ResponseEntity.ok(postService.reprocessPost(postId));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> saveComment(@PathVariable("postId") long postId) {
        return ResponseEntity.ok(commentService.save(postId));
    }
}
