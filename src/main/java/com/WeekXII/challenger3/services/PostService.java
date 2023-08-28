package com.WeekXII.challenger3.services;

import com.WeekXII.challenger3.model.Post;

import java.util.List;

public interface PostService {

    Post processPost(long id);

    Post disablePost(long id);

    List<Post> getAllPosts(int pageNo, int pageSize);

    Post reprocessPost(long id);

    void validateId(long id);

    void postExists(long id);

    //Post findById(long id);
}
