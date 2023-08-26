package com.WeekXII.challenger3.repositories;

import com.WeekXII.challenger3.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
