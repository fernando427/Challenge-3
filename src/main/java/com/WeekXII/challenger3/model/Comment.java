package com.WeekXII.challenger3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private Long id;
    private String name;
    private String email;
    private String body;

    @ManyToOne
    @Getter(AccessLevel.NONE)
    private Post post;

}
