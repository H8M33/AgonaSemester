package com.technokratos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Table (name = "post")
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostEntity extends AbstractEntity{

    private String title;

    private String post_text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
