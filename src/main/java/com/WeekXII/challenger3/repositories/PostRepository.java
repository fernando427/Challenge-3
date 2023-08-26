package com.WeekXII.challenger3.repositories;

import com.WeekXII.challenger3.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByStatus(String status, Pageable pageable);
}
