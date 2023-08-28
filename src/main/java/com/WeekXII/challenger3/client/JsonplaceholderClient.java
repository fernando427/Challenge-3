package com.WeekXII.challenger3.client;

import com.WeekXII.challenger3.client.response.JsonplaceholderCommentResponse;
import com.WeekXII.challenger3.client.response.JsonplaceholderPostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "JsonplaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonplaceholderClient {
    @GetMapping("/posts")
    List<JsonplaceholderPostResponse> getAllPost();

    @GetMapping("/posts/{id}")
    JsonplaceholderPostResponse getPost(@PathVariable long id);

    @GetMapping("/posts/{id}/comments")
    List<JsonplaceholderCommentResponse> getComment(@PathVariable long id);
}
