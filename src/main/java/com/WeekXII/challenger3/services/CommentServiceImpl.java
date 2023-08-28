package com.WeekXII.challenger3.services;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderCommentResponse;
import com.WeekXII.challenger3.client.response.JsonplaceholderPostResponse;
import com.WeekXII.challenger3.exceptions.ResourceNotFoundException;
import com.WeekXII.challenger3.exceptions.ValueAlreadyExistsException;
import com.WeekXII.challenger3.model.Comment;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.CommentRepository;
import com.WeekXII.challenger3.repositories.HistoryRepository;
import com.WeekXII.challenger3.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final JsonplaceholderClient jsonplaceholderClient;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              JsonplaceholderClient jsonplaceholderClient,
                              ModelMapper mapper) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.jsonplaceholderClient = jsonplaceholderClient;
        this.mapper = mapper;

    }

    @Override
    public List<Comment> save(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        List<JsonplaceholderCommentResponse> jsonplaceholderCommentResponse = jsonplaceholderClient.getComment(id);
        List<Comment> commentSave = jsonplaceholderCommentResponse.stream().map(p -> mapper.map(p, Comment.class)).collect(Collectors.toList());

        commentRepository.saveAll(commentSave);

        return commentSave;

    }
}
