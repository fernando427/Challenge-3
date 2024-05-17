package com.WeekXII.challenger3.services.impl;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.exceptions.IdValueOutOfBoundException;
import com.WeekXII.challenger3.exceptions.ResourceNotFoundException;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.PostRepository;
import com.WeekXII.challenger3.services.HistoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

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
    private ModelMapper modelMapper;

    @Mock
    private JsonplaceholderClient jsonplaceholderClient;

    @Test
    void processPost() {

    }

    @Test
    void disablePost() {
    }

    @Test
    void getAllPosts() {
    }

    @Test
    void reprocessPost() {
    }

    @Test
    void whenPostExistsThrowsError() {
        /*
        Post postTest = new Post(1L, 1L, "T", "T", "T", null, null);

        when(postRepository.findById(1L)).thenReturn(Optional.of(postTest));

        assertThrows(ValueAlreadyExistsException.class, () -> postService.postExists(1L));
        */
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

        Post postTest = new Post(1L, 1L, "Title", "Content", "Summary", null, null);

        when(postRepository.findById(1L)).thenReturn(Optional.of(postTest));


        Post response = postService.findById(1L);

        assertNotNull(response);
        assertEquals(postTest, response);
        assertEquals(Post.class, response.getClass());
        assertEquals(1L, response.getId());
    }

    @Test
    void whenFindByIdThenReturnNotFoundException() {

        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.findById(anyLong()));

    }

    private void startPost() {

    }
}