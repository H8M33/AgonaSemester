package com.technokratos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name ="account_chat",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns =@JoinColumn(name = "chat_id")
    )
    private List<ChatEntity> chats;

    @OneToMany
    private List<PostEntity> posts;
}
