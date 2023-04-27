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
@Table(name = "chat")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity extends AbstractEntity{

    @Column(nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(
            name ="account_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns =@JoinColumn(name = "account_id")
    )
    private List<UserEntity> users;

    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> messages;
}
