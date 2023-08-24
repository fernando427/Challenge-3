package com.WeekXII.challenger3.services;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderResponse;
import com.WeekXII.challenger3.exceptions.ResourceNotFoundException;
import com.WeekXII.challenger3.exceptions.ValueAlreadyExists;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final JsonplaceholderClient jsonplaceholderClient;

    public PostServiceImpl(PostRepository postRepository, JsonplaceholderClient jsonplaceholderClient) {
        this.postRepository = postRepository;
        this.jsonplaceholderClient = jsonplaceholderClient;
    }

    @Override
    public Post save(long id) {
        Post postSave = null;
        boolean postExist = postExists(id);
        try {
            if(!postExist){
                JsonplaceholderResponse jsonplaceholderResponse = jsonplaceholderClient.getPost(id);
                postSave = Post.builder().id(jsonplaceholderResponse.getId()).userId(jsonplaceholderResponse.getUserId()).body(jsonplaceholderResponse.getBody()).title(jsonplaceholderResponse.getTitle()).build();
                return postRepository.save(postSave);
            } else {
                throw new ValueAlreadyExists("Post", "ID", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postSave;
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

    private boolean postExists(long postId) {
        Optional<Post> existingPost = postRepository.findById(postId);
        return existingPost.isPresent();
    }
}
