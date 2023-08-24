package com.WeekXII.challenger3.repositories;

import com.WeekXII.challenger3.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
