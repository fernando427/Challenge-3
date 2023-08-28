package com.WeekXII.challenger3.client.response;

import com.WeekXII.challenger3.model.Post;
import lombok.Data;

@Data
public class JsonplaceholderCommentResponse {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
