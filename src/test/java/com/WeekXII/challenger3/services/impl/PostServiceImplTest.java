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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private HistoryService historyService;

    @Mock
    private ModelMapper mapper;

    @Mock
    private JsonplaceholderClient jsonplaceholderClient;

    private Post post;
    private Post post2;
    private JsonplaceholderPostResponse jsonplaceholderPostResponse;

    @BeforeEach
    void setUp() {
        startPost();
    }

    @Test
    void whenProcessPostThenReturnAnPostSaved() {
        when(jsonplaceholderClient.getPost(1L)).thenReturn(jsonplaceholderPostResponse);
        when(mapper.map(jsonplaceholderPostResponse, Post.class)).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post response = postService.processPost(1L);

        assertNotNull(response);
        assertEquals("ENABLED", response.getStatus());
        verify(jsonplaceholderClient).getPost(1L);
        verify(mapper).map(jsonplaceholderPostResponse, Post.class);
        verify(postRepository).save(post);
        verify(historyService).saveHistory("ENABLED", post);
    }

    @Test
    void whenDisablePostIsEnabledThenReturnAnPostDisabled() {
        post.setStatus("ENABLED");
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post response = postService.disablePost(1L);

        assertNotNull(response);
        assertEquals("DISABLED", response.getStatus());
        verify(postRepository).save(post);
        verify(historyService).saveHistory("DISABLED", post);
    }

    @Test
    void whenDisablePostIsAlreadyDisabledThenReturnAnException() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        assertThrows(StatusAlreadyDisabledException.class, () -> postService.disablePost(1L));
    }

    @Test
    void whenGetAllPostsThenReturnAnListOfPosts() {
        post.setStatus("ENABLED");
        post2.setStatus("ENABLED");
        Pageable pageable = PageRequest.of(0, 10);
        List<Post> postList = Arrays.asList(post, post2);
        Page<Post> postPage = new PageImpl<>(postList, pageable, postList.size());

        when(postRepository.findByStatus("ENABLED", pageable)).thenReturn(postPage);
        when(mapper.map(post, Post.class)).thenReturn(post);
        when(mapper.map(post2, Post.class)).thenReturn(post2);

        List<Post> result = postService.getAllPosts(0, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(post, result.get(0));
        assertEquals(post2, result.get(1));

        verify(postRepository).findByStatus("ENABLED", pageable);
    }

    @Test
    void whenReprocessPostThenReturnAnPostEnabled() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post response = postService.reprocessPost(post.getId());

        assertNotNull(response);
        assertEquals("ENABLED", response.getStatus());
        verify(postRepository).save(post);
        verify(historyService).saveHistory("ENABLED", post);
    }

    @Test
    void whenPostExistsThenThrowValueAlreadyExistsException() {

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        assertThrows(ValueAlreadyExistsException.class, () -> postService.postExists(post.getId()));

    }

    @Test
    void whenPostDoesNotExistsThenDoNotThrowException() {

        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> postService.postExists(post.getId()));

    }

    @Test
    void whenValidateIdInBound() {
        assertDoesNotThrow(() -> postService.validateId(1));
        assertDoesNotThrow(() -> postService.validateId(100));
    }

    @Test
    void whenValidateIdOutOfBound() {
        assertThrows(IdValueOutOfBoundException.class, () -> postService.validateId(0));
        assertThrows(IdValueOutOfBoundException.class, () -> postService.validateId(101));
    }

    @Test
    void whenFindByIdThenReturnAnPostInstance() {

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        Post response = postService.findById(anyLong());

        assertNotNull(response);
        assertEquals(post, response);
        assertEquals(Post.class, response.getClass());
        assertEquals(1L, response.getId());
    }

    @Test
    void whenFindByIdThenReturnNotFoundException() {

        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.findById(anyLong()));
    }

    private void startPost() {
        post = new Post(1L, 1L, "Title", "Content", "DISABLED", null, null);
        post2 = new Post(2L, 2L, "Title 2", "Content 2", "DISABLED", null, null);
        jsonplaceholderPostResponse = new JsonplaceholderPostResponse();
        jsonplaceholderPostResponse.setId(1L);
        jsonplaceholderPostResponse.setUserId(1L);
        jsonplaceholderPostResponse.setBody("Body");
        jsonplaceholderPostResponse.setTitle("Title");
    }
}