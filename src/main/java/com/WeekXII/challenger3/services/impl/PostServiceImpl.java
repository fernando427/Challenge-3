package com.WeekXII.challenger3.services.impl;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderPostResponse;
import com.WeekXII.challenger3.exceptions.IdValueOutOfBoundException;
import com.WeekXII.challenger3.exceptions.ResourceNotFoundException;
import com.WeekXII.challenger3.exceptions.StatusAlreadyDisabledException;
import com.WeekXII.challenger3.exceptions.ValueAlreadyExistsException;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.PostRepository;
import com.WeekXII.challenger3.services.HistoryService;
import com.WeekXII.challenger3.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final JsonplaceholderClient jsonplaceholderClient;
    private final HistoryService historyService;
    private final ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,
                           JsonplaceholderClient jsonplaceholderClient,
                           ModelMapper mapper,
                           HistoryService historyService) {
        this.postRepository = postRepository;
        this.jsonplaceholderClient = jsonplaceholderClient;
        this.mapper = mapper;
        this.historyService = historyService;
    }

    @Override
    public Post processPost(long id) {
        validateId(id);
        postExists(id);
        JsonplaceholderPostResponse jsonplaceholderPostResponse = jsonplaceholderClient.getPost(id);
        Post postSave = mapper.map(jsonplaceholderPostResponse, Post.class);
        postSave.setStatus("ENABLED");
        postRepository.save(postSave);
        historyService.saveHistory("ENABLED", postSave);
        return postSave;
    }

    @Override
    public Post disablePost(long id) {
        validateId(id);
        Post postExist = findById(id);
        if(postExist.getStatus() == "ENABLED"){
            postExist.setStatus("DISABLED");
            historyService.saveHistory("DISABLED", postExist);
            return postRepository.save(postExist);
        } else {
            throw new StatusAlreadyDisabledException();
        }
    }

    @Override
    public List<Post> getAllPosts(int pageNo, int pageSize) {
        String enabledStatus = "ENABLED";
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Post> postPages = postRepository.findByStatus(enabledStatus, pageable);
        List<Post> posts = postPages.stream().map(p -> mapper.map(p, Post.class)).collect(Collectors.toList());
        return posts;
    }

    @Override
    public Post reprocessPost(long id) {
        validateId(id);
        Post postExist = findById(id);
        postExist.setStatus("ENABLED");
        historyService.saveHistory("ENABLED", postExist);
        return postRepository.save(postExist);
    }


    public void postExists(long theId) {
        Optional<Post> result = postRepository.findById(theId);
        if(result.isPresent()) {
            throw new ValueAlreadyExistsException("Post", "ID", theId);
        }
    }

    public void validateId(long id) {
        if (id < 1 || id > 100) {
            throw new IdValueOutOfBoundException("Post", id);
        }
    }

    public Post findById(long theId) {
        Optional<Post> result = postRepository.findById(theId);

        Post thePost = null;

        if(result.isPresent()) {
            thePost = result.get();
            thePost.setUserId(result.get().getUserId());
            thePost.setId(result.get().getId());
            thePost.setBody(result.get().getBody());
            thePost.setTitle(result.get().getTitle());
            thePost.setStatus(result.get().getStatus());
            return thePost;
        } else {
            throw new ResourceNotFoundException("Post", "ID", theId);
        }
    }
}
