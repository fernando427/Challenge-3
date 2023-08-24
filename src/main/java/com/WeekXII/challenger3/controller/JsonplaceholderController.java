package com.WeekXII.challenger3.controller;

import com.WeekXII.challenger3.exceptions.ValueAlreadyExists;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.services.PostService;
import org.apache.coyote.Response;
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
        return new ResponseEntity<>(postService.save(postId), HttpStatus.CREATED);
    }
}
