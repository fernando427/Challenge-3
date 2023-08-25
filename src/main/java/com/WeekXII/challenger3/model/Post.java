package com.WeekXII.challenger3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(unique=true)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

}
