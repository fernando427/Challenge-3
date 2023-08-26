package com.WeekXII.challenger3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    private Long postNumber;

    @Id
    private Long id;
    private String name;
    private String email;
    private String body;

    @ManyToOne
    private Post post;

}
