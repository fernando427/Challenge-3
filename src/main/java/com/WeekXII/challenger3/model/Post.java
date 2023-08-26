package com.WeekXII.challenger3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    private Long id;
    private Long userId;
    private String title;
    private String body;
    private String status;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}
