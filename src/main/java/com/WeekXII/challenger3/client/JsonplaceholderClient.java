package com.WeekXII.challenger3.client;

import com.WeekXII.challenger3.client.response.JsonplaceholderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "JsonplaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonplaceholderClient {
    @GetMapping("/posts")
    List<JsonplaceholderResponse> getPosts();

    @GetMapping("/posts/{id}")
    JsonplaceholderResponse getPost(@PathVariable long id);
}
