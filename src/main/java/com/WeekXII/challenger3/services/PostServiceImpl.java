package com.WeekXII.challenger3.services;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderResponse;
import com.WeekXII.challenger3.exceptions.ValueAlreadyExistsException;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.HistoryRepository;
import com.WeekXII.challenger3.repositories.PostRepository;
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
    private final HistoryRepository historyRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,
                           JsonplaceholderClient jsonplaceholderClient,
                           ModelMapper mapper,
                           HistoryRepository historyRepository) {
        this.postRepository = postRepository;
        this.jsonplaceholderClient = jsonplaceholderClient;
        this.mapper = mapper;
        this.historyRepository = historyRepository;
    }

    @Override
    public Post save(long id) {
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

        Post postSave = null;
        Post postExist = postExists(id);
        if(postExist == null){

            JsonplaceholderResponse jsonplaceholderResponse = jsonplaceholderClient.getPost(id);
            postSave = mapper.map(jsonplaceholderResponse, Post.class);
            postSave.setStatus("ENABLED");

            return postRepository.save(postSave);
        } else {
            throw new ValueAlreadyExistsException("Post", "ID", id);
        }

        /*
            Falta adicionar tratamentos de erro para quando o ID não existir
            e para quando o ID não for válido.
        */
    }

    /*
    Disabilita um post, fazendo com que o mesmo não entre na paginação.
    */
    @Override
    public Post disablePost(long id) {
        Post postExist = postExists(id);
        if(postExist != null && postExist.getStatus() == "ENABLED"){
            postExist.setStatus("DISABLED");
            return postRepository.save(postExist);
        } else {
            throw new ValueAlreadyExistsException("Post", "ID", id);
        }
    }

    /*
    Listagem de todas as postagens salvas no banco de dados sem o status "DISABLED".
    */
    @Override
    public List<Post> getAllPosts(int pageNo, int pageSize) {
        String enabledStatus = "ENABLED";

        Pageable pageable= PageRequest.of(pageNo,pageSize);

        Page<Post> postPages = postRepository.findByStatus(enabledStatus, pageable);

        List<Post> posts = postPages.stream().map(p -> mapper.map(p, Post.class)).collect(Collectors.toList());

        return posts;
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
            thePost.setStatus(result.get().getStatus());
            return thePost;
        } else {
            return thePost;
        }
    }



    /*
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
            thePost.setStatus(result.get().getStatus());
            return thePost;
        } else {
            throw new ValueAlreadyExistsException("Post", "ID", theId);
        }
    }*/
}
