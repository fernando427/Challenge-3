package com.WeekXII.challenger3.services.impl;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderCommentResponse;
import com.WeekXII.challenger3.model.Comment;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.CommentRepository;
import com.WeekXII.challenger3.services.CommentService;
import com.WeekXII.challenger3.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final JsonplaceholderClient jsonplaceholderClient;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostService postService,
                              JsonplaceholderClient jsonplaceholderClient,
                              ModelMapper mapper) {

        this.commentRepository = commentRepository;
        this.postService = postService;
        this.jsonplaceholderClient = jsonplaceholderClient;
        this.mapper = mapper;

    }

    @Override
    public List<Comment> save(long id) {
        Post post = postService.findById(id);
        List<JsonplaceholderCommentResponse> jsonplaceholderCommentResponse = jsonplaceholderClient.getComment(id);
        List<Comment> commentSave = jsonplaceholderCommentResponse.stream().map(p -> mapper.map(p, Comment.class)).collect(Collectors.toList());
        commentRepository.saveAll(commentSave);
        return commentSave;
    }
}
