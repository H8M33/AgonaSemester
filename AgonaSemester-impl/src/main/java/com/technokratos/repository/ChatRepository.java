package com.technokratos.repository;

import com.technokratos.model.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {

    Optional<ChatEntity> findById(UUID id);

    Optional<ChatEntity> findDistinctChatByUsers_Id(UUID chatId);
}
