package com.WeekXII.challenger3.services.impl;

import com.WeekXII.challenger3.client.JsonplaceholderClient;
import com.WeekXII.challenger3.client.response.JsonplaceholderCommentResponse;
import com.WeekXII.challenger3.model.Comment;
import com.WeekXII.challenger3.model.Post;
import com.WeekXII.challenger3.repositories.CommentRepository;
import com.WeekXII.challenger3.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostService postService;

    @Mock
    private JsonplaceholderClient jsonplaceholderClient;

    @Mock
    private ModelMapper mapper;

    private Post post;
    private List<JsonplaceholderCommentResponse> jsonplaceholderCommentResponse;

    @BeforeEach
    void setUp() {
        startComment();
    }

    @Test
    void whenCommentSaveThenReturnComment() {
        when(postService.findById(anyLong())).thenReturn(post);
        when(jsonplaceholderClient.getComment(anyLong())).thenReturn(jsonplaceholderCommentResponse);
        when(mapper.map(any(JsonplaceholderCommentResponse.class), eq(Comment.class))).thenAnswer(invocation -> {
            JsonplaceholderCommentResponse response = (JsonplaceholderCommentResponse) invocation.getArguments()[0];
            Comment comment = new Comment();
            comment.setId(response.getId());
            comment.setName(response.getName());
            comment.setEmail(response.getEmail());
            comment.setBody(response.getBody());
            return comment;
        });

        List<Comment> savedComments = commentService.save(1L);

        assertNotNull(savedComments);
        assertEquals(jsonplaceholderCommentResponse.size(), savedComments.size());
        for (int i = 0; i < jsonplaceholderCommentResponse.size(); i++) {
            assertEquals(jsonplaceholderCommentResponse.get(i).getId(), savedComments.get(i).getId());
            assertEquals(jsonplaceholderCommentResponse.get(i).getName(), savedComments.get(i).getName());
            assertEquals(jsonplaceholderCommentResponse.get(i).getEmail(), savedComments.get(i).getEmail());
            assertEquals(jsonplaceholderCommentResponse.get(i).getBody(), savedComments.get(i).getBody());
        }
        verify(commentRepository, times(1)).saveAll(savedComments);
    }

    private void startComment() {
        post = new Post(1L, 1L, "Title", "Content", "DISABLED", null, null);
        jsonplaceholderCommentResponse = Arrays.asList(
                createJsonplaceholderCommentResponse(1L, 1L, "Name", "Email", "Body"),
                createJsonplaceholderCommentResponse(2L, 1L, "Name", "Email", "Body")
        );
    }

    private JsonplaceholderCommentResponse createJsonplaceholderCommentResponse(Long id, Long postId, String name, String email, String body) {
        JsonplaceholderCommentResponse response = new JsonplaceholderCommentResponse();
        ReflectionTestUtils.setField(response, "id", id);
        ReflectionTestUtils.setField(response, "postId", postId);
        ReflectionTestUtils.setField(response, "name", name);
        ReflectionTestUtils.setField(response, "email", email);
        ReflectionTestUtils.setField(response, "body", body);
        return response;
    }
}