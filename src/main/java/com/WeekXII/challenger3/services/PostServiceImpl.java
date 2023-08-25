package com.WeekXII.challenger3.services;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderResponse;
import com.WeekXII.challenger3.exceptions.ResourceNotFoundException;
import com.WeekXII.challenger3.exceptions.ValueAlreadyExistsException;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final JsonplaceholderClient jsonplaceholderClient;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,
                           JsonplaceholderClient jsonplaceholderClient,
                           ModelMapper mapper) {
        this.postRepository = postRepository;
        this.jsonplaceholderClient = jsonplaceholderClient;
        this.mapper = mapper;
    }

    @Override
    public Post save(long id) {
        Post postSave = null;
        /*try {
            Post postExist = postExists(id);
            if(postExist == null){
                JsonplaceholderResponse jsonplaceholderResponse = jsonplaceholderClient.getPost(id);
                postSave = mapper.map(jsonplaceholderResponse, Post.class);
                return postRepository.save(postSave);
            } else {
                throw new ValueAlreadyExistsException("Post", "ID", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/

        Post postExist = postExists(id);
        if(postExist == null){
            JsonplaceholderResponse jsonplaceholderResponse = jsonplaceholderClient.getPost(id);
            postSave = mapper.map(jsonplaceholderResponse, Post.class);
            return postRepository.save(postSave);
        } else {
            throw new ValueAlreadyExistsException("Post", "ID", id);
        }
    }

    @Override
    public Post findById(long theId) {
        Optional<Post> result = postRepository.findById(theId);

        Post thePost = null;

        if(result.isPresent()) {
            thePost = result.get();
            thePost.setUserId(result.get().getUserId());
            thePost.setId(result.get().getId());
            thePost.setBody(result.get().getBody());
            thePost.setTitle(result.get().getTitle());
        } else {
            throw new ResourceNotFoundException("Post", "id", theId);
        }

        return thePost;
    }

    private Post postExists(long theId) {
        Optional<Post> result = postRepository.findById(theId);

        Post thePost = null;

        if(result.isPresent()) {
            thePost = result.get();
            thePost.setUserId(result.get().getUserId());
            thePost.setId(result.get().getId());
            thePost.setBody(result.get().getBody());
            thePost.setTitle(result.get().getTitle());
            return thePost;
        } else {
            return thePost;
        }
    }
}
