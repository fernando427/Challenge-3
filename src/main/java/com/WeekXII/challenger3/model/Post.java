package com.WeekXII.challenger3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
@Builder
public class Post {

    @Column(name = "userId")
    private Long userId;

    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

}
