package com.WeekXII.challenger3.services;

import com.WeekXII.challenger3.model.Post;

import java.util.List;

public interface PostService {

    Post save(long id);

    Post disablePost(long id);

    List<Post> getAllPosts(int pageNo, int pageSize);

    //Post findById(long id);
}
